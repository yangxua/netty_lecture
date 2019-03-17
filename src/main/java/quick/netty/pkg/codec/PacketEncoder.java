package quick.netty.pkg.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import quick.netty.pkg.Package;
import quick.netty.pkg.PacketCodeC;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 11:09
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Package> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Package msg, ByteBuf out) throws Exception {
        PacketCodeC.getInstance().encode(out, msg);
    }
}