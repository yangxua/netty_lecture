package im.serializer;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 14:23
 * @Description:
 */
public interface Serializer {

    /**
     * 默认序列化方式
     */
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 获取序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     * @param o
     * @return
     */
    byte[] serialize(Object o);

    /**
     * 反序列化
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}