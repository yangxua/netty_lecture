package quick.netty.pkg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/3/14 19:51
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponsePackage extends Package {

    private String userId;

    private String userName;

    /**
     * 错误理由
     */
    private String reason;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 是否成功
     */
    private boolean flag;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}