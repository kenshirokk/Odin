package com.kenshiro.vertx.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SimpleTimer extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        //verticle内部创建的定时器, 会在verticle卸载的时候自动停止
        vertx.setPeriodic(500, e -> {
            log.debug("tick tock");
        });
    }

    public static void main(String[] args) {
        Vertx v = Vertx.vertx();
        Future<String> f = v.deployVerticle(new SimpleTimer());
        f.onSuccess(e -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            v.undeploy(e);
        });
    }
}
