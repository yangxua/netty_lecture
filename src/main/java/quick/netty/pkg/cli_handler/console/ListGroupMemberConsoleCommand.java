package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.ListGroupMemberRequestPackage;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:17
 * @Description:
 */
public class ListGroupMemberConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入要列出人员的组ID：");

        String groupId = sc.next();

        ListGroupMemberRequestPackage requestPackage = new ListGroupMemberRequestPackage();
        requestPackage.setGroupId(groupId);

        channel.writeAndFlush(requestPackage);
    }
}