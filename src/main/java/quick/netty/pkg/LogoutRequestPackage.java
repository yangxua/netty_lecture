package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 09:46
 * @Description:
 */
public class LogoutRequestPackage extends Package {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}