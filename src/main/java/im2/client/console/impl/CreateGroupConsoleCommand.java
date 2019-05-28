package im2.client.console.impl;

import im2.client.console.ConsoleCommand;
import im2.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 21:31
 * @Description:
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");

        String userIds = scanner.next();

        CreateGroupRequestPacket requestPacket = new CreateGroupRequestPacket();
        requestPacket.setUserIdList(Arrays.asList(userIds.split(SPLITER)));

        channel.writeAndFlush(requestPacket);
    }
}