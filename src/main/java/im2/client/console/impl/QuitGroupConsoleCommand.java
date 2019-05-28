package im2.client.console.impl;

import im2.client.console.ConsoleCommand;
import im2.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:27
 * @Description:
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，退出群聊：");

        String groupId = scanner.next();

        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}