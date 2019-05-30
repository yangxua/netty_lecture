package im2.protocol;

import im2.protocol.command.Command;
import im2.protocol.request.*;
import im2.protocol.response.*;
import im2.serializer.Serializer;
import im2.serializer.SerializerAlgorithm;
import im2.serializer.impl.JAVASerializer;
import im2.serializer.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:09
 * @Description:
 */
public final class PacketCodec {

    public static final short MAGIC = (short) 0xbabe;

    public final static PacketCodec INSTANCE = new PacketCodec();

    private static final Map<Byte, Serializer> serializeAlgorithm2Serialize;
    private static final Map<Byte, Class<? extends Packet>> command2Packet;

    static {
        command2Packet = new HashMap<>();
        command2Packet.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        command2Packet.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        command2Packet.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        command2Packet.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        command2Packet.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        command2Packet.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        command2Packet.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        command2Packet.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        command2Packet.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        command2Packet.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        command2Packet.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        command2Packet.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        command2Packet.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        command2Packet.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        command2Packet.put(Command.SEND_TO_USER_GROUP_REQUEST, SendToGroupRequestPacket.class);
        command2Packet.put(Command.SEND_TO_USER_GROUP_RESPONSE, SendToGroupResponsePacket.class);
        command2Packet.put(Command.HEART_BEAT_REQUEST, HeartBeatRequestPacket.class);
        command2Packet.put(Command.HEART_BEAT_RESPONSE, HeartBeatResponsePacket.class);


        serializeAlgorithm2Serialize = new HashMap<>();
        serializeAlgorithm2Serialize.put(SerializerAlgorithm.JSON, new JSONSerializer());
        serializeAlgorithm2Serialize.put(SerializerAlgorithm.JAVA, new JAVASerializer());
    }

    private PacketCodec(){}

    public ByteBuf encode(ByteBuf buf, Packet packet) {
        if (null == packet) {
            throw new IllegalArgumentException("packet can't be null");
        }

        byte[] bytes = getSerializer(packet.getSerializer()).serialize(packet);

        buf.writeShort(MAGIC);
        buf.writeByte(packet.getVersion());
        buf.writeByte(packet.getSerializer());
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        return buf;
    }

    public Packet decode(ByteBuf buf) {
        if (null == buf) {
            throw new IllegalArgumentException("buf can't be null");
        }

        buf.skipBytes(2);
        buf.skipBytes(1);

        //获取序列化算法
        byte serializerAlgorithm = buf.readByte();
        //获取command
        byte command = buf.readByte();

        //获取packet数据
        int len = buf.readInt();
        byte[] bytes = new byte[len];
        buf.readBytes(bytes);

        Serializer serializer = getSerializer(serializerAlgorithm);
        Class<? extends Packet> clazz = getPacketClazz(command);

        if (null != serializer && clazz != null) {
            return serializer.deserialize(bytes, clazz);
        }

        return null;
    }

    private Serializer getSerializer(byte serializer) {
        return serializeAlgorithm2Serialize.get(serializer);
    }

    private Class<? extends Packet> getPacketClazz(byte command) {
        return command2Packet.get(command);
    }
}