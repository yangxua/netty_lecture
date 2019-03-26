package im.client.concole.impl;

import im.client.concole.ConsoleCommand;
import im.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 21:38
 * @Description:
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要加入的群成员：");

        String uids = scanner.next();
        List<String> userIds = Arrays.asList(uids.split(","));
        CreateGroupRequestPacket requestPacket = new CreateGroupRequestPacket();
        requestPacket.setUserIds(userIds);

        channel.writeAndFlush(requestPacket);
    }
}