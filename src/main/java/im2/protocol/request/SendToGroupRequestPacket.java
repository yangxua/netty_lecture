package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/5/29 21:03
 * @Description:
 */
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SendToGroupRequestPacket extends Packet {

    /**
     * 组id
     */
    private String groupId;

    /**
     * 消息
     */
    private String msg;

    @Override
    public byte getCommand() {
        return Command.SEND_TO_USER_GROUP_REQUEST;
    }
}