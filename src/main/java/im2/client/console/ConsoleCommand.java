package im2.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 21:26
 * @Description:
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}