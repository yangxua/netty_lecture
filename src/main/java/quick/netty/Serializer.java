package quick.netty;

/**
 * @Auther: allanyang
 * @Date: 2019/3/12 20:30
 * @Description:
 */
public interface Serializer {

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}