package com.kenshiro.concurrent.test;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test9 {

    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.debug("尝试获得锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("被打断");
                e.printStackTrace();
                return;
            }
            try {
                log.debug("获得到锁");
            } finally {
                lock.unlock();
            }

        }, "t1");

        lock.lock();
        t1.start();
        Sleeper.sleep(1);
        t1.interrupt();

    }
}
