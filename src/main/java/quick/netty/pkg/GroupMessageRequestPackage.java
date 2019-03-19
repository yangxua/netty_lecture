package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 18:07
 * @Description:
 */
public class GroupMessageRequestPackage extends Package {

    private String groupId;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}