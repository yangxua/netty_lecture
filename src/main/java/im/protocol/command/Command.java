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

    /**
     * 创建组请求指令
     */
    Byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建子相应指令
     */
    Byte CREATE_GROUP_RESPONSE = 6;

    /**
     * 加入组请求指令
     */
    Byte JOIN_GROUP_REQUEST = 7;

    /**
     * 加入组请求指令
     */
    Byte JOIN_GROUP_RESPONSE = 8;

    /**
     * 推出组请求指令
     */
    Byte QUIT_GROUP_REQUEST = 9;

    /**
     * 退出组响应指令
     */
    Byte QUIT_GROUP_RESPONSE = 10;

    /**
     * 列出组成员请求指令
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 11;

    /**
     * 列出组成员响应指令
     */
    Byte LIST_GROUP_MEMBERS_RESPONSE = 12;
}