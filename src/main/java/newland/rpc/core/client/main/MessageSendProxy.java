package newland.rpc.core.client.main;

import newland.rpc.core.client.handler.MessageSendHandler;
import newland.rpc.model.MessageCallBack;
import newland.rpc.model.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 11:37
 * @Description:
 */
public class MessageSendProxy<T> implements InvocationHandler {

    public MessageSendProxy(Class<T> cls) {
        this.cls = cls;
    }

    private Class<T> cls;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameterVals(args);
        request.setTypeParameters(method.getParameterTypes());

        MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = handler.sendMessage(request);
        return callBack.start();
    }

}