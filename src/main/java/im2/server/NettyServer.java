package im2.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: allanyang
 * @Date: 2019/5/15 17:26
 * @Description:
 */
public class NettyServer {

    private static final int MAX_PORT = 65536;
    private static final int MIN_PORT = 0;

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerPipeline());

        bind(bootstrap, 8888);
    }

    private static void bind(ServerBootstrap bootstrap, int port) {
        if (port >= MAX_PORT || port <= MIN_PORT) {
            return ;
        }

        bootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("绑定端口" + port + "成功!");
            } else {
                System.out.println("绑定端口" + port+ "失败!");
                bind(bootstrap, port+1);
            }
        });
    }
}