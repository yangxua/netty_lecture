package sx.rpc;

/**
 * @Auther: allanyang
 * @Date: 2019/7/10 16:37
 * @Description:
 */
public enum MessageType {

    SERVICE_REQ((byte)0),
    SERVICE((byte)1),
    ONE_WAY((byte)2),
    LOGIN_REQ((byte) 3),
    LOGIN_RESP((byte) 4),
    HEARTBEAT_REQ((byte) 5),
    HEARTBEAT_RESP((byte) 6);
    ;

    private byte value;
    private MessageType(byte value) {
        this.value = value;
    }

    public byte value() {
        return this.value;
    }
}