package im2.protocol;

import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Auther: allanyang
 * @Date: 2019/5/30 16:29
 * @Description:
 */
public class HeartBeats {

    private static final ByteBuf HEARTBEAT_BUF;
    private static final int HEART_BEAT_LENGTH = 9;

    static {
        ByteBuf buf = Unpooled.buffer(HEART_BEAT_LENGTH);
        buf.writeShort(PacketCodec.MAGIC);
        buf.writeByte(Command.HEART_HBAT);
        buf.writeByte(SerializerAlgorithm.JSON);
        buf.writeByte(1);
        HEARTBEAT_BUF = Unpooled.unmodifiableBuffer(Unpooled.unreleasableBuffer(buf));
    }

    public static ByteBuf heartBeatContent() {
        return HEARTBEAT_BUF.duplicate();
    }
}