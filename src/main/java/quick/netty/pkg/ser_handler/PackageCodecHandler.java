package quick.netty.pkg.ser_handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import quick.netty.pkg.Package;
import quick.netty.pkg.PacketCodeC;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 19:31
 * @Description:
 */
@ChannelHandler.Sharable
public class PackageCodecHandler  extends MessageToMessageCodec<ByteBuf,Package> {

    public static final PackageCodecHandler INSTANCE = new PackageCodecHandler();
    private PackageCodecHandler(){}

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodeC.getInstance().decode(ctx.channel().alloc().ioBuffer()));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Package msg, List<Object> out) throws Exception {
        out.add(PacketCodeC.getInstance().encode(ctx.channel().alloc().ioBuffer(), msg));
    }
}