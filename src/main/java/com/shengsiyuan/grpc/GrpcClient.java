package com.shengsiyuan.grpc;

import com.shengsiyuan.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @Auther: allanyang
 * @Date: 2019/1/31 16:40
 * @Description:
 */
public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8899).usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub studentServiceBlockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
       /* MyResponse response = studentServiceBlockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zs").build());

        System.out.println(response.getRealname());*/

        /*Iterator<StudentResponse> iter = studentServiceBlockingStub.getStudentByAge(StudentRequest.newBuilder().setAge(20).build());

        while (iter.hasNext()) {
            System.out.println(iter.next().getName());
        }*/

        System.out.println("-------------------------");


        StreamObserver<StudentResponseList> streamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach(studentResponse -> {
                    System.out.println(studentResponse.getName());
                    System.out.println(studentResponse.getCity());
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("compeleted");
            }
        };

        StreamObserver<StudentRequest> studentsWrapperByAge = stub.getStudentsWrapperByAge(streamObserver);

        studentsWrapperByAge.onNext(StudentRequest.newBuilder().setAge(10).build());
        studentsWrapperByAge.onNext(StudentRequest.newBuilder().setAge(20).build());

        studentsWrapperByAge.onCompleted();

        Thread.sleep(5000);
    }
}