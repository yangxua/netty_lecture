package quick.netty;

/**
 * @Auther: allanyang
 * @Date: 2019/3/12 20:12
 * @Description:
 */
public abstract class Package {

    /**
     * 版本号
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}