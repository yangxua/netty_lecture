package newland.rpc.core.server.main;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import newland.rpc.core.server.initialize.MessageRecvChannelInitializer;
import newland.rpc.core.thread.NameThreadFactory;
import newland.rpc.core.thread.RpcThreadPool;
import newland.rpc.model.MessageKeyValue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 15:29
 * @Description:
 */
public class MessageRecvExecutor implements ApplicationContextAware, InitializingBean {

    private String serverAddress;
    private static final String DELIMETER = ":";

    private Map<String, Object> handlerMap = new ConcurrentHashMap<>();

    private static ThreadPoolExecutor threadPoolExecutor;

    public MessageRecvExecutor() {}

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public MessageRecvExecutor(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public static void execute(Runnable task) {
        if (threadPoolExecutor == null) {
            synchronized (MessageRecvExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16 , -1);
                }
            }
        }
        threadPoolExecutor.submit(task);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ThreadFactory threadFactory = new NameThreadFactory("NettyRPC ThreadFactory");

        int parallel = Runtime.getRuntime().availableProcessors() * 2;

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MessageRecvChannelInitializer(handlerMap));

            String[] ipAddr = serverAddress.split(DELIMETER);
            if (ipAddr.length == 2) {
                String ip = ipAddr[0];
                int port = Integer.valueOf(ipAddr[1]);
                ChannelFuture channelFuture = b.bind(ip, port).sync();
                System.out.printf("[author allanyang] Netty RPC Server start success!\n");
                channelFuture.channel().closeFuture().sync();
            }else {
                System.out.printf("[author allanyang] Netty RPC Server start fail!\n");
            }
        } finally {
            System.out.println(111);
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            MessageKeyValue keyValue = (MessageKeyValue)applicationContext.getBean(Class.forName("newland.rpc.model.MessageKeyValue"));
            Map<String, Object> rpcServiceObject = keyValue.getMessageKeyVal();

            Set<Map.Entry<String, Object>> entries = rpcServiceObject.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                handlerMap.put(entry.getKey(), entry.getValue());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
