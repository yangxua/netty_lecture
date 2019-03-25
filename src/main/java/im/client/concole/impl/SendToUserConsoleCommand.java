package im.client.concole.impl;

import im.client.concole.ConsoleCommand;
import im.protocol.request.SendToUserRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 11:32
 * @Description:
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("发送消息给某个用户：");

        String userId = scanner.next();
        String msg = scanner.next();

        channel.writeAndFlush(new SendToUserRequestPacket(userId, msg));
    }
}