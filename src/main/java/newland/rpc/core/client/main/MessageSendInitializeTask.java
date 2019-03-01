package newland.rpc.core.client.main;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import newland.rpc.core.client.initialize.MessageSendChannelInitializer;
import newland.rpc.core.client.handler.MessageSendHandler;

import java.net.InetSocketAddress;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 14:36
 * @Description:
 */
public class MessageSendInitializeTask implements Runnable{

    private EventLoopGroup eventLoopGroup = null;
    private InetSocketAddress socketAddress = null;
    private RpcServerLoader rpcServerLoader = null;

    public MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress socketAddress, RpcServerLoader rpcServerLoader) {
        this.eventLoopGroup = eventLoopGroup;
        this.socketAddress = socketAddress;
        this.rpcServerLoader = rpcServerLoader;
    }

    @Override
    public void run() {
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true).handler(new MessageSendChannelInitializer());

        ChannelFuture channelFuture = b.connect(socketAddress);
        channelFuture.addListener((ChannelFutureListener) future -> {
            MessageSendHandler messageSendHandler = future.channel().pipeline().get(MessageSendHandler.class);
            MessageSendInitializeTask.this.rpcServerLoader.setMessageSendHandler(messageSendHandler);
        });
    }
}