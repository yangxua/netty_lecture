package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/5/18 12:31
 * @Description:
 */
@Data
public class LoginResponsePacket extends Packet {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    @Override
    protected byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    @Override
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}