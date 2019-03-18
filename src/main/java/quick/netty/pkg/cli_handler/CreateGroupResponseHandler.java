package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.CreateGroupResponsePackage;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 20:02
 * @Description:
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePackage msg) throws Exception {
        System.out.print("群创建成功，id 为[" + msg.getGroupId() + "], ");
        System.out.println("群里面有：" + msg.getUserNames());
    }
}