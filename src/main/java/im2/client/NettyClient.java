package im2.client;

import im2.protocol.PacketCodec;
import im2.protocol.request.MessageRequestPacket;
import im2.thread.ApplicationThreadPoolExecutor;
import im2.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/5/15 17:25
 * @Description:
 */
public class NettyClient {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 8888;
    private static final int MAX_PORT = 65536;
    private static final int MIN_PORT = 0;
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientPipeline());

        connect(bootstrap, IP, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String ip, int port, int retry) {
        if (StringUtils.isBlank(ip) || port >= MAX_PORT || port <=MIN_PORT) {
            return ;
        }

        bootstrap.connect(ip, port).addListener(future -> {
           if (future.isSuccess()) {
               System.out.println("连接客户端成功!");

               Channel channel = ((ChannelFuture) future).channel();
               asyncConsoleThread(channel);
           } else if (retry == 0){
               System.out.println("连接次数已用尽!");
           } else {
               //第几次重连
               int time  = (MAX_PORT - retry) + 1;
               //本次重连间隔时间
               int delay = 1 << time;
               System.err.println(new Date() + ": 连接失败，第" + time + "次重连……");

               bootstrap.config().group().schedule(() ->
                   connect(bootstrap, ip, port, retry-1), delay, TimeUnit.SECONDS
               );
           }
        });
    }

    private static void asyncConsoleThread(Channel channel) {
        Runnable r = () -> {
            while(!Thread.interrupted()) {
                if (LoginUtil.isLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    MessageRequestPacket requestPacket = new MessageRequestPacket();
                    requestPacket.setMessage(line);

                    ByteBuf buf = PacketCodec.INSTANCE.encode(channel.alloc(), requestPacket);

                    channel.writeAndFlush(buf);
                }
            }
        };

        ApplicationThreadPoolExecutor.getExecutor().execute(r);
    }
}