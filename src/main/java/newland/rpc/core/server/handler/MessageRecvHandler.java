package newland.rpc.core.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import newland.rpc.core.server.main.MessageRecvExecutor;
import newland.rpc.core.server.main.MessageRecvInitializeTask;
import newland.rpc.model.MessageRequest;
import newland.rpc.model.MessageResponse;

import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 16:12
 * @Description:
 */
public class MessageRecvHandler extends SimpleChannelInboundHandler<MessageRequest> {

    private Map<String, Object> handlerMap = null;

    public MessageRecvHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) throws Exception {
        MessageResponse response = new MessageResponse();
        MessageRecvExecutor.execute(new MessageRecvInitializeTask(msg, response, handlerMap, ctx));
    }
}