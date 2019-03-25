package im.protocol.command;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 14:07
 * @Description:
 */
public interface Command {

    /**
     * 登陆请求指令
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登陆响应指令
     */
    Byte LOGIN_RESPONSE = 2;

    /**
     * 发送信息指令
     */
    Byte SEND_TO_USER_REQUEST = 3;

    /**
     * 接受信息指令
     */
    Byte SEND_TO_USER_RESPONSE = 4;
}