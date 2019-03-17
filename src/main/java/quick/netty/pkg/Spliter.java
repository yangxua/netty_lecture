package quick.netty.pkg;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 12:46
 * @Description:
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

        private static final int LENGTH_FIELD_OFFSET = 7;
        private static final int LENGTH_FIELD_LENGTH = 4;

        public Spliter() {
            super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
        }

        @Override
        protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            // 屏蔽非本协议的客户端
            if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC) {
                ctx.channel().close();
                return null;
            }

            return super.decode(ctx, in);
        }
}