package im2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
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
}