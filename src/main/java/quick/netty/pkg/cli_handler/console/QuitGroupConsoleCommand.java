package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.QuitGroupRequestPackage;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:16
 * @Description:
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入要退出的组Id:");

        String groupId = sc.next();

        QuitGroupRequestPackage requestPackage = new QuitGroupRequestPackage();
        requestPackage.setGroupId(groupId);

        channel.writeAndFlush(requestPackage);
    }
}