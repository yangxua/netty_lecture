package quick.netty.pkg;

import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/12 20:12
 * @Description:
 */
@Data
public abstract class Package {

    /**
     * 版本号
     */
    private Byte version = 1;

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }

    /**
     * 指令
     * @return
     */

    public abstract Byte getCommand();
}