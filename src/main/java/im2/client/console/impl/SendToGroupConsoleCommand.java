package im2.client.console.impl;

import im2.client.console.ConsoleCommand;
import im2.protocol.request.SendToGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/29 21:02
 * @Description:
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个组：");

        String groupId = scanner.next();
        String msg = scanner.next();

        SendToGroupRequestPacket requestPacket = new SendToGroupRequestPacket();
        requestPacket.setGroupId(groupId);
        requestPacket.setMsg(msg);

        channel.writeAndFlush(requestPacket);
    }
}