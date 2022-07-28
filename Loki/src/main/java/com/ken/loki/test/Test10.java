package com.kenshiro.concurrent.test;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test10 {

    static boolean hasCigarette = false;
    static boolean hasTakeout = false;
    private static ReentrantLock room = new ReentrantLock();
    private static Condition waitYan = room.newCondition();
    private static Condition waitFan = room.newCondition();

    // 虚假唤醒
    public static void main(String[] args) {

        new Thread(() -> {
            room.lock();
            try {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        waitYan.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");
            }  finally {
                room.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            room.lock();
            try {
                log.debug("外卖送到没？[{}]", hasTakeout);
                while (!hasTakeout) {
                    log.debug("没外卖，先歇会！");
                    try {
                        waitFan.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");
            } finally {
                room.unlock();
            }
        }, "小女").start();

        Sleeper.sleep(1);
        new Thread(() -> {
            room.lock();
            try {
                hasTakeout = true;
                log.debug("外卖到了噢！");
                waitFan.signal();
            } finally {
                room.unlock();
            }
        }, "送外卖的").start();

        Sleeper.sleep(1);
        new Thread(() -> {
            room.lock();
            try {
                hasCigarette = true;
                log.debug("烟到了噢！");
                waitYan.signal();
            } finally {
                room.unlock();
            }
        }, "送烟的").start();

    }
}
