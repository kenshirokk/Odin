package com.kenshiro.vertx.core;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        log.debug("start");
    }

    @Override
    public void stop() throws Exception {
        log.debug("stop");
    }

    public static void main(String[] args) {
        Vertx v = Vertx.vertx();

        JsonObject json = new JsonObject().put("name", "zhangsan");
        DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(json);

        Future<String> future = v.deployVerticle("com.kenshiro.vertx.core.SimpleVerticle", deploymentOptions);
        System.out.println(System.getenv("JAVA_HOME"));
        Context orCreateContext = v.getOrCreateContext();
        System.out.println(orCreateContext);
        System.out.println(v.getOrCreateContext());
        System.out.println(v.getOrCreateContext());
        v.setTimer(1000, event -> log.debug("1"));
        v.setPeriodic(2000, event -> log.debug("periodic"));
    }
}
