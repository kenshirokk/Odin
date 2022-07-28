package com.kenshiro.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        log.debug("isInterrupted   {}", t1.isInterrupted());
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        log.debug("isInterrupted   {}", t1.isInterrupted());
    }
}
