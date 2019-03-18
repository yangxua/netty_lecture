package quick.netty.pkg.cli_handler.console;

import io.netty.channel.Channel;
import quick.netty.pkg.session.SessionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 09:29
 * @Description:
 */
public class ConsoleCommandMgr implements ConsoleCommand{

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandMgr() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
    }


    @Override
    public void exec(Scanner sc, Channel channel) {
        String command = sc.next();

        if (!SessionUtils.hasLogin(channel)) {
            return ;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(sc, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}