package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:05
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

}