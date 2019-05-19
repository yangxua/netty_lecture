package im2.codec;

import im2.protocol.Packet;
import im2.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 12:44
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, msg);
    }
}