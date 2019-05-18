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
    byte LOGIN_RESPINSE = 2;
}