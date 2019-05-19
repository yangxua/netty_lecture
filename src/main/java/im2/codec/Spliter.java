package im2.codec;

import im2.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 13:12
 * @Description: 解决拆包粘包问题
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 5;
    private static final int LENGTH_FIELD_LENGTH = 4;


    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        if (in.readShort() != PacketCodec.MAGIC) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}