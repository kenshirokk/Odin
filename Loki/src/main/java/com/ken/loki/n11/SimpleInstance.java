package com.kenshiro.vertx.core;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class SimpleInstance {

    public static void main(String[] args) {
        //获得vertx实例
        Vertx vertx = Vertx.vertx();

        //带配置的实例
        //VertxOptions ↓
        //clustering, high availability, pool sizes and various other settings
        Vertx vertxWithOption = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));

        vertx.setPeriodic(1000, event -> {
            System.out.println(event + Thread.currentThread().getName());
        });


        Future<String> future = vertx.createDnsClient().lookup("baidu.com");
        future.toCompletionStage().whenComplete((ip, err) -> {
            if (err != null) {
                System.err.println("Could not resolve vertx.io");
                System.out.println(Thread.currentThread().getName());
                err.printStackTrace();
            } else {
                System.out.println("vertx.io => " + ip);
            }
        });
    }
}
