package im2.client.console.impl;

import im2.client.console.ConsoleCommand;
import im2.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 21:31
 * @Description:
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = scanner.next();
        String msg = scanner.next();

        channel.writeAndFlush(new MessageRequestPacket(toUserId, msg));
    }
}