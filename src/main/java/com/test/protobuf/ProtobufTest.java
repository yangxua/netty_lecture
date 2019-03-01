package com.test.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Auther: allanyang
 * @Date: 2019/1/29 15:58
 * @Description:
 */
public class ProtobufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student zs = DataInfo.Student.newBuilder().setName("zs").setId(1).setEmail("qq.com").build();

        byte[] bytes = zs.toByteArray();

        DataInfo.Student student = DataInfo.Student.parseFrom(bytes);
        System.out.println(student);
    }
}