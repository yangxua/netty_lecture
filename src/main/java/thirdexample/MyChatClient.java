package thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: allanyang
 * @Date: 2019/1/23 20:24
 * @Description:
 */
public class MyChatClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(bossGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
            Channel channel = b.connect("localhost", 8765).sync().channel();

            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

            for( ; ; ) {
                channel.writeAndFlush(bufr.readLine() + "\r\n");
            }
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}