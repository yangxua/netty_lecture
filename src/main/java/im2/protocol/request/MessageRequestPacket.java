package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 10:27
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageRequestPacket extends Packet {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 消息内容
     */
    private String message;

    @Override
    protected byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    @Override
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}