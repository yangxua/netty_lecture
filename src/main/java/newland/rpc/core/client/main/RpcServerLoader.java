package newland.rpc.core.client.main;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import newland.rpc.core.thread.RpcThreadPool;
import newland.rpc.core.client.handler.MessageSendHandler;
import newland.rpc.serialize.RpcSerializeProtocol;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 13:57
 * @Description:
 */
public class RpcServerLoader {

    private volatile static RpcServerLoader rpcServerLoader;
    public static final String DELIMITER = ":";
    private RpcSerializeProtocol rpcSerializeProtocol = RpcSerializeProtocol.JDKSERIALIZE;

    //获取并行线程最大数
    private static final int parallel = Runtime.getRuntime().availableProcessors() * 2;
    //netty nio线程池
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(parallel,-1);
    private MessageSendHandler messageSendHandler = null;

    //等待netty连接建立通知
    private Lock lock = new ReentrantLock();
    private Condition siginal = lock.newCondition();

    private RpcServerLoader(){}

    public static RpcServerLoader getInstance() {
        if (rpcServerLoader == null) {
            synchronized (RpcServerLoader.class) {
                if (rpcServerLoader == null) {
                    rpcServerLoader = new RpcServerLoader();
                }
            }
        }
        return rpcServerLoader;
    }

    public void load(String serverAddress) {
        String[] ipAddr = serverAddress.split(DELIMITER);
        if (ipAddr.length == 2) {
            String ip = ipAddr[0];
            int port = Integer.valueOf(ipAddr[1]);
            final InetSocketAddress socketAddress = new InetSocketAddress(ip, port);

            threadPoolExecutor.submit(new MessageSendInitializeTask(eventLoopGroup, socketAddress, this));
        }
    }

    public void setMessageSendHandler(MessageSendHandler messageSendHandler) {
        try {
            lock.lock();
            this.messageSendHandler = messageSendHandler;
            siginal.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public MessageSendHandler getMessageSendHandler() throws InterruptedException {
        try {
            lock.lock();
            if (messageSendHandler == null) {
                siginal.await();
            }
            return messageSendHandler;
        } finally {
            lock.unlock();
        }
    }

    public void unload() {
        messageSendHandler.close();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }

    public void setRpcSerializeProtocol(RpcSerializeProtocol rpcSerializeProtocol) {
        this.rpcSerializeProtocol = rpcSerializeProtocol;
    }
}