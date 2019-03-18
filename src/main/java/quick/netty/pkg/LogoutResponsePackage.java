package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 20:13
 * @Description:
 */
public class LogoutResponsePackage extends Package {

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    /**
     * 是否成功

     */
    private boolean isSuccess;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}