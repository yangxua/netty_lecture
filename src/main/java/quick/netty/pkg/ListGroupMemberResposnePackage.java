package quick.netty.pkg;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:51
 * @Description:
 */
public class ListGroupMemberResposnePackage extends Package {


    private boolean isSuccess;

    private String groupId;

    private List<String> userNames;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBER_RESPONSE;
    }
}