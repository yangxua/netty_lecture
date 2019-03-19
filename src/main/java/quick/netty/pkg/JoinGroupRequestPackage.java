package quick.netty.pkg;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:19
 * @Description:
 */
public class JoinGroupRequestPackage extends Package {

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 组id
     */
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}