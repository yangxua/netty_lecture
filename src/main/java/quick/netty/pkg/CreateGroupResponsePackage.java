package quick.netty.pkg;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 19:57
 * @Description:
 */
public class CreateGroupResponsePackage extends Package {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 用户名
     */
    private List<String> userNames;

    /**
     * 组id
     */
    private String groupId;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}