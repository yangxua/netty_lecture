package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/12 20:26
 * @Description:
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}