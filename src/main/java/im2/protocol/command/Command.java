package im2.protocol.command;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:07
 * @Description:
 */
public interface Command {

    /**
     * 心跳包
     */
    byte HEART_HBAT = 0;

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

    /**
     * 加入组请求
     */
    byte JOIN_GROUP_REQUEST = 9;

    /**
     * 加入组响应
     */
    byte JOIN_GROUP_RESPONSE = 10;

    /**
     * 退出组请求
     */
    byte QUIT_GROUP_REQUEST = 11;

    /**
     * 退出组响应
     */
    byte QUIT_GROUP_RESPONSE = 12;

    /**
     * 列出成员请求
     */
    byte LIST_GROUP_MEMBERS_REQUEST = 13;

    /**
     * 列出成员响应
     */
    byte LIST_GROUP_MEMBERS_RESPONSE = 14;

    /**
     * 发送给某个组消息请求
     */
    byte SEND_TO_USER_GROUP_REQUEST = 15;

    /**
     * 发送给某个组消息响应
     */
    byte SEND_TO_USER_GROUP_RESPONSE = 16;

    /**
     * 心跳包请求
     */
    byte HEART_BEAT_REQUEST = 17;

    /**
     * 心跳包响应
     */
    byte HEART_BEAT_RESPONSE = 18;
}