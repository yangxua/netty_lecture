package im2.client.console.impl;

import im2.client.console.ConsoleCommand;
import im2.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 21:30
 * @Description:
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        channel.writeAndFlush(new LogoutRequestPacket());
    }
}