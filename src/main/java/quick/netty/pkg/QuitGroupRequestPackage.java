package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:36
 * @Description:
 */
public class QuitGroupRequestPackage extends Package {

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}