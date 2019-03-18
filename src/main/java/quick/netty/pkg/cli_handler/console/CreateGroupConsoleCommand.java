package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.CreateGroupRequestPackage;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 09:34
 * @Description:
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLIT = ",";

    public CreateGroupConsoleCommand() {
    }

    @Override
    public void exec(Scanner sc, Channel channel) {
        CreateGroupRequestPackage requestPackage = new CreateGroupRequestPackage();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");

        String userId = sc.next();
        requestPackage.setUserIds(Arrays.asList(userId.split(USER_ID_SPLIT)));

        channel.writeAndFlush(requestPackage);
    }
}