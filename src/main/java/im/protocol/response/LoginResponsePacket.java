package im.protocol.response;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 14:20
 * @Description:
 */
@Data
public class LoginResponsePacket extends Packet {

    private String userId;
    private String userName;
    private boolean isSuccess;
    private String reason;

    @Override
    protected Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}