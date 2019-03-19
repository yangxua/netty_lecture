package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.JoinGroupRequestPackage;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:16
 * @Description:
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入要加入的groupId:");

        JoinGroupRequestPackage requestPackage = new JoinGroupRequestPackage();
        String groupId = sc.next();
        requestPackage.setGroupId(groupId);

        channel.writeAndFlush(requestPackage);
    }
}