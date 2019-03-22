package im.client.concole;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 11:37
 * @Description:
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}