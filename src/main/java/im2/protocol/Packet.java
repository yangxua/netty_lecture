package im2.protocol;

import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:04
 * @Description:
 */
@Data
public abstract class Packet {

    /**
     * 版本号
     */
    private byte version = 1;

    /**
     * 获取指令
     */
    protected abstract byte getCommand();

    /**
     * 获取序列化方法
     */
    protected abstract byte getSerializer();
}