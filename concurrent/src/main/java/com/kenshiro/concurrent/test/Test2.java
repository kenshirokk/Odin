package com.kenshiro.concurrent.test;

public class Test2 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                System.out.println("t1-----" + count++);
            }
        });
        Thread t2 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                System.out.println("\tt2-----" + count++);
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}
