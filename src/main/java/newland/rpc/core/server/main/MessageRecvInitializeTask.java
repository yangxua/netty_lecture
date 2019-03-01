package newland.rpc.core.server.main;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import newland.rpc.model.MessageRequest;
import newland.rpc.model.MessageResponse;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 16:16
 * @Description:
 */
public class MessageRecvInitializeTask implements Runnable {

    private MessageRequest request = null;
    private MessageResponse response = null;
    private Map<String, Object> map = null;
    private ChannelHandlerContext ctx = null;

    public MessageRecvInitializeTask(MessageRequest request, MessageResponse response, Map<String, Object> map, ChannelHandlerContext ctx) {
        this.request = request;
        this.response = response;
        this.map = map;
        this.ctx = ctx;
    }

    public MessageResponse getResponse() {
        return response;
    }

    public MessageRequest getRequest() {
        return request;
    }

    public void setRequest(MessageRequest request) {
        this.request = request;
    }

    @Override
    public void run() {
        response.setMessageId(request.getMessageId());
        try {
            Object res = reflect(request);
            response.setResult(res);
        } catch (Throwable t) {
            response.setError(t.toString());
            t.printStackTrace();
            System.err.printf("RPC Server invoke error!\n");
        }

        ctx.writeAndFlush(response).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("RPC Server Send message-id respone:" + request.getMessageId());
            }
        });
    }

    private Object reflect(MessageRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String className = request.getClassName();
        Object serviceBean = map.get(className);
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameterVals();
        return MethodUtils.invokeMethod(serviceBean, methodName, parameters);
    }
}