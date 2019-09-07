package sx.rpc.struct;

/**
 * @Auther: allanyang
 * @Date: 2019/7/9 21:34
 * @Description:
 */
public final class NettyMessage {

    private Header header;

    private Object body;

    public final Header getHeader() {
        return header;
    }
    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage [header=" + header + "]";
    }
}