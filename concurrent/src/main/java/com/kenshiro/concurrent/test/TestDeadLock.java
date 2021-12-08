package com.kenshiro.concurrent.test;

import com.kenshiro.concurrent.unit.Sleeper;

public class TestDeadLock {

    public static void main(String[] args) {

        Object a = new Object();
        Object b = new Object();

        new Thread(() -> {
            synchronized (a) {
                Sleeper.sleep(1);
                synchronized (b) {
                    System.out.println("t1");
                }
            }
        },"T1").start();

        new Thread(() -> {
            synchronized (b) {
                Sleeper.sleep(1);
                synchronized (a) {
                    System.out.println("t2");
                }
            }
        }, "t2").start();
    }
}
