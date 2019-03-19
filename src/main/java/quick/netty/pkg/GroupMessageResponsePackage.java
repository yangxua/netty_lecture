package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 18:09
 * @Description:
 */
public class GroupMessageResponsePackage extends Package {

    private String groupId;

    private String userName;

    private String message;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}