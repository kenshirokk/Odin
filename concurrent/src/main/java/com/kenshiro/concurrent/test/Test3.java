package com.kenshiro.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("run");
            log.debug("park");
            LockSupport.park();
            log.debug("unpark");
        }, "t1");
        t1.start();

        log.debug("main run");
        TimeUnit.SECONDS.sleep(1);
        log.debug("main after 1s");
        t1.interrupt();
    }

}
