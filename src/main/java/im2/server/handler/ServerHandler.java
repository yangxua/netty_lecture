package im2.server.handler;

import im2.protocol.Packet;
import im2.protocol.PacketCodec;
import im2.protocol.request.LoginRequestPacket;
import im2.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: allanyang
 * @Date: 2019/5/18 12:26
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(buf);

        if (null == packet) {
            throw new IllegalArgumentException("packet is null");
        }

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(packet.getVersion());

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            if (valid(loginRequestPacket)) {
                responsePacket.setSuccess(true);
                responsePacket.setMsg("登陆成功!");
            } else {
                responsePacket.setSuccess(false);
                responsePacket.setMsg("用户名或密码错误!");
            }
        }

        ctx.channel().writeAndFlush(PacketCodec.INSTANCE.encode(ctx.alloc(), responsePacket));
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if (null == loginRequestPacket) {
            return false;
        }
        return "allan".equalsIgnoreCase(loginRequestPacket.getUserName()) && "pwd".equalsIgnoreCase(loginRequestPacket.getPassword());
    }
}