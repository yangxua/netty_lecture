package com.shengsiyuan.grpc;

import com.shengsiyuan.proto.*;
import io.grpc.stub.StreamObserver;

/**
 * @Auther: allanyang
 * @Date: 2019/1/31 16:29
 * @Description:
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(com.shengsiyuan.proto.MyRequest request, io.grpc.stub.StreamObserver<com.shengsiyuan.proto.MyResponse> responseObserver) {
        System.out.println("接收到客户端请求：" + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentByAge(com.shengsiyuan.proto.StudentRequest request,
                                io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StudentResponse> responseObserver) {
        System.out.println("接收到客户端请求：" + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("zs").setCity("beijing").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("ls").setCity("shanghai").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("ww").setCity("tianjin").build());

        responseObserver.onCompleted();
    }


    @Override
    public io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StudentRequest> getStudentsWrapperByAge(
            io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("接受参数：" + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse s1 = StudentResponse.newBuilder().setName("zs").setCity("bj").build();
                StudentResponse s2 = StudentResponse.newBuilder().setName("ls").setCity("sh").build();
                StudentResponseList list = StudentResponseList.newBuilder().addStudentResponse(s1).addStudentResponse(s2).build();

                responseObserver.onNext(list);
                responseObserver.onCompleted();
            }
        };
    }
}