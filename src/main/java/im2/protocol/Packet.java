package im2.protocol;

import im2.serializer.SerializerAlgorithm;
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
    public abstract byte getCommand();

    /**
     * 获取序列化方法
     */
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}