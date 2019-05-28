package im2.protocol.command;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:07
 * @Description:
 */
public interface Command {

    /**
     * 登陆请求
     */
    byte LOGIN_REQUEST = 1;

    /**
     * 登陆返回
     */
    byte LOGIN_RESPONSE = 2;

    /**
     * 消息请求
     */
    byte MESSAGE_REQUEST = 3;

    /**
     * 消息返回
     */
    byte MESSAGE_RESPONSE = 4;

    /**
     * 创建组请求
     */
    byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建组响应
     */
    byte CREATE_GROUP_RESPONSE = 6;

    /**
     * 退出组请求
     */
    byte LOGOUT_REQUEST = 7;

    /**
     * 退出组响应
     */
    byte LOGOUT_RESPONSE = 8;
}