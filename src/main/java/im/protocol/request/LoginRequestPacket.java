package im.protocol.request;


import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 14:08
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userName;
    private String password;

    @Override
    protected Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}