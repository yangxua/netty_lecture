package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 20:24
 * @Description:
 */
public class HeartbeatResponsePackage extends Package {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}