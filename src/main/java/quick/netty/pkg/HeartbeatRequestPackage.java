package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 20:26
 * @Description:
 */
public class HeartbeatRequestPackage extends Package {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}