package com.kenshiro.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class TestCorrectPostureStep1 {
    static final Object room = new Object();
    static boolean hasCigarette = false; // 有没有烟
    static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            log.debug("==============");
            m1();
            TimeUnit.SECONDS.sleep(3);
        }
    }

    public static void m1() {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                if (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("可以开始干活了");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
        new Thread(() -> {
            synchronized (room) {
                log.debug("可以开始干活了");
            }
        }, "其它人").start();
        }

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(() -> {
//            // 这里能不能加 synchronized (room)？
//            synchronized (room) {
//                hasCigarette = true;
//                log.debug("烟到了噢！");
//            }
//        }, "送烟的").start();
    }
}
