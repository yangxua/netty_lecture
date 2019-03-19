package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:48
 * @Description:
 */
public class ListGroupMemberRequestPackage extends Package {
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBER_REQUSET;
    }
}