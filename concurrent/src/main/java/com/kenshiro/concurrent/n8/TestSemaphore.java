package com.kenshiro.concurrent.n8;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class TestSemaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    log.debug("running");
                    Sleeper.sleep(1);
                    log.debug("end");
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
