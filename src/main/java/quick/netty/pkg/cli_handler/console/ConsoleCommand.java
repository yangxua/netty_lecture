package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 09:26
 * @Description:
 */
public interface ConsoleCommand {

    void exec(Scanner sc, Channel channel);
}