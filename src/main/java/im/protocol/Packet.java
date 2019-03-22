package im.protocol;

import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 14:06
 * @Description:
 */
@Data
public abstract class Packet {

    /**
     * 版本号
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    protected abstract Byte getCommand();
}