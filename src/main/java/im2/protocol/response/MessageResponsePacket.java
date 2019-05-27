package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 10:28
 * @Description:
 */
@Data
public class MessageResponsePacket extends Packet {

    /**
     * 用户id
     */
    private String fromUserId;

    /**
     * 用户名
     */
    private String fromUserName;

    /**
     * 消息内容
     */
    private String message;

    @Override
    protected byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    @Override
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}