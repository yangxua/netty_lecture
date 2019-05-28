package im2.client.console.impl;

import im2.client.console.ConsoleCommand;
import im2.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:06
 * @Description:
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，加入群聊：");

        String groupId = scanner.next();

        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}