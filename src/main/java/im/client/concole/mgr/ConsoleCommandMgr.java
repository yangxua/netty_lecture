package im.client.concole.mgr;

import im.client.concole.ConsoleCommand;
import im.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 11:37
 * @Description:
 */
public class ConsoleCommandMgr implements ConsoleCommand {

    public Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandMgr() {
        consoleCommandMap = new HashMap<>();
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.next();

        if (!SessionUtil.hasLogin(channel)) {
            return ;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (command != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}