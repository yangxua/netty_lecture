package im.client.concole.impl;

import im.client.concole.ConsoleCommand;
import im.protocol.request.SendToGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 17:56
 * @Description:
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入要发送群的id:");

        String groupId = scanner.next();
        String msg = scanner.next();

        SendToGroupRequestPacket requestPacket = new SendToGroupRequestPacket();
        requestPacket.setGroupId(groupId);
        requestPacket.setMessage(msg);

        channel.writeAndFlush(requestPacket);
    }
}