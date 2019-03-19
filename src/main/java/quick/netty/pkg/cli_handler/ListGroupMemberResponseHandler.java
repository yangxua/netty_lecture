package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.ListGroupMemberResposnePackage;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:55
 * @Description:
 */
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResposnePackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResposnePackage msg) throws Exception {
        System.out.println("群[" + msg.getGroupId() + "]中的人包括：" + msg.getUserNames());
    }
}