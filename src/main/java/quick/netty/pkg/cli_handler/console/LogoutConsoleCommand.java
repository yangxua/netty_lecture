package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.LogoutRequestPackage;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 09:34
 * @Description:
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        LogoutRequestPackage requestPackage = new LogoutRequestPackage();

        channel.writeAndFlush(requestPackage);
    }
}