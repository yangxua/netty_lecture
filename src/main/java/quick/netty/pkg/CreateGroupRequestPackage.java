package quick.netty.pkg;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 13:39
 * @Description:
 */
public class CreateGroupRequestPackage extends Package {

    private List<String> userIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public CreateGroupRequestPackage() {

    }

    public CreateGroupRequestPackage(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}