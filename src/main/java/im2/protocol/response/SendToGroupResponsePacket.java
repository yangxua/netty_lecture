package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/5/29 21:05
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendToGroupResponsePacket extends Packet {

    /**
     * 发送消息的人姓名
     */
    private String userName;

    /**
     * 发送的消息
     */
    private String msg;

    @Override
    protected byte getCommand() {
        return Command.SEND_TO_USER_GROUP_RESPONSE;
    }
}