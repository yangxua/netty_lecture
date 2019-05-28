package im2.client.console.mgr;

import im2.client.console.ConsoleCommand;
import im2.client.console.impl.*;
import im2.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 21:26
 * @Description:
 */
public class ConsoleCommandMgr {

    private final Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandMgr() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
    }

    public void handle(Scanner scanner, Channel channel) {
        if (!SessionUtil.hasLogin(channel)) {
            return;
        }

        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
            handle(scanner, channel);
        }
    }
}