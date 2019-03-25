package im.protocol.request;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 11:37
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendToUserRequestPacket extends Packet {

    private String userId;
    private String message;

    @Override
    protected Byte getCommand() {
        return Command.SEND_TO_USER_REQUEST;
    }
}