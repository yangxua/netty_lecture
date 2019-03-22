package im.client.concole.impl;

import im.client.concole.ConsoleCommand;
import im.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 11:39
 * @Description:
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket requestPacket = new LoginRequestPacket();

        System.out.println("请输入你的用户名：");
        String name = scanner.nextLine();
        requestPacket.setUserName(name);
        requestPacket.setPassword("pwd");

        channel.writeAndFlush(requestPacket);
        waitForResponse();
    }

    private void waitForResponse() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}