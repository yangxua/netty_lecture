package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;
import lombok.Data;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 09:34
 * @Description:
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 组id
     */
    private String groupId;

    /**
     * 用户名
     */
    private List<String> userNameList;

    @Override
    protected byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }

    @Override
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}