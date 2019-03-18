package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.LoginRequestPackage;

import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 09:40
 * @Description:
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        LoginRequestPackage requestPackage = new LoginRequestPackage();

        System.out.println("输入用户名登录:");

        requestPackage.setUsername(sc.nextLine());
        requestPackage.setPassword("pwd");

        channel.writeAndFlush(requestPackage);
        waitForResponse();
    }

    private void waitForResponse() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
    }
}