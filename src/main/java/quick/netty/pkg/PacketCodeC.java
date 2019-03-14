package quick.netty.pkg;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/3/13 17:42
 * @Description:
 */
public class PacketCodeC {

    private static final int MAGIC = 0x12345678;
    private static final PacketCodeC INSTANCE = new PacketCodeC();
    private PacketCodeC(){}

    public static PacketCodeC getInstance() {
        return INSTANCE;
    }

    private static final Map<Byte, Class<? extends Package>> packageTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packageTypeMap = new HashMap<>();
        packageTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPackage.class);
        packageTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePackage.class);


        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBufAllocator bufAllocator, Package pkg) {
        //创建buf
        ByteBuf buf = bufAllocator.ioBuffer();

        //序列化pkg
        byte[] serialize = Serializer.DEFAULT.serialize(pkg);

        buf.writeInt(MAGIC);
        buf.writeByte(pkg.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buf.writeByte(pkg.getCommand());
        buf.writeByte(serialize.length);
        buf.writeBytes(serialize);

        return buf;
    }

    public Package decode(ByteBuf buf) {
        //跳过魔术
        buf.skipBytes(4);

        //跳过版本号
        buf.skipBytes(1);

        byte serializeAlgorithm  = buf.readByte();

        byte command = buf.readByte();

        byte len = buf.readByte();

        byte[] bytes = new byte[len];
        buf.readBytes(bytes);

        Class<? extends Package> requestType = getRequestType(command);
        
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType,bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Package> getRequestType(byte command) {
        return packageTypeMap.get(command);
    }
}