package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.MessageRequestPackage;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 09:33
 * @Description:
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        String userId = sc.nextLine();
        String name = sc.nextLine();

        channel.writeAndFlush(new MessageRequestPackage(userId, name));
    }
}