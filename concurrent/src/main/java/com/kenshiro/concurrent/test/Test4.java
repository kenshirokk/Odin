package com.kenshiro.concurrent.test;

import java.util.concurrent.atomic.AtomicInteger;

public class Test4 {

    public static int count = 0;
    public static volatile int count1 = 0;
    public static AtomicInteger count2 = new AtomicInteger(0);
    public static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count3 = new AtomicInteger(0);
        long begin = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (lock) {
                    count++;
                    count1++;
                    count2.incrementAndGet();
                    count3.incrementAndGet();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (lock) {
                    count--;
                    count1--;
                    count2.decrementAndGet();
                    count3.decrementAndGet();
                }
            }
        });
        t2.start();

        t1.join();
        t2.join();
        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);

        System.out.println(System.currentTimeMillis() - begin);
    }
}
