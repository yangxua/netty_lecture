package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:23
 * @Description:
 */
public class JoinGroupResponsePackage extends Package {

    private boolean isSuccess;

    private String reason;

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}