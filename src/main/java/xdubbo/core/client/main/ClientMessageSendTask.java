package xdubbo.core.client.main;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import xdubbo.model.MessageRequest;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 16:07
 * @Description:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ClientMessageSendTask implements Runnable {

    private MessageRequest request;
    private Bootstrap bootstrap;
    private String ip;
    private int port;

    @Override
    public void run() {
        bootstrap.connect(ip, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("client success");

                Channel channel = ((ChannelFuture) future).channel();
                channel.writeAndFlush(request);
                //channel.closeFuture();
            }
        });
    }
}