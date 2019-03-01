package newland.rpc.core.client.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import newland.rpc.model.MessageCallBack;
import newland.rpc.model.MessageRequest;
import newland.rpc.model.MessageResponse;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 14:45
 * @Description:
 */
public class MessageSendHandler extends SimpleChannelInboundHandler<MessageResponse> {

    private ConcurrentHashMap<String, MessageCallBack> mapCallBack = new ConcurrentHashMap<String, MessageCallBack>();

    private Channel channel;
    private SocketAddress address;

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getAddress() {
        return address;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.address = this.channel.remoteAddress();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) throws Exception {
        String messageId = msg.getMessageId();
        MessageCallBack callBack = mapCallBack.get(messageId);
        if (callBack != null) {
            mapCallBack.remove(messageId);
            callBack.over(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public MessageCallBack sendMessage(MessageRequest request) {
        MessageCallBack messageCallBack = new MessageCallBack(request);
        mapCallBack.put(request.getMessageId(), messageCallBack);
        channel.writeAndFlush(request);
        return messageCallBack;
    }
}