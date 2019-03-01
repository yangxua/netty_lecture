package com.shengsiyuan.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @Auther: allanyang
 * @Date: 2019/1/31 16:34
 * @Description:
 */
public class GrpcServer {

    private Server server;

    public void start() throws IOException {
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();

        System.out.println("server started");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭JVM");
            GrpcServer.this.stop();
        }));

        System.out.println("执行到这里");
    }

    public void stop() {
        if (server != null) {
            this.server.shutdown();
        }
    }

    public void awaitTermination() throws InterruptedException {
        if (server != null) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();

        server.start();
        server.awaitTermination();
    }
}