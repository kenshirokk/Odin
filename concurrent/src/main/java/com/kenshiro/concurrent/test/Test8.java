package com.kenshiro.concurrent.test;

import java.util.concurrent.locks.ReentrantLock;

public class Test8 {

    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("main");
            m1();
        } finally {
            lock.unlock();
        }
    }

    public static void m1() {
        lock.lock();
        try {
            System.out.println("m1");
            m2();
        }finally {
            lock.unlock();
        }
    }

    public static void m2() {
        lock.lock();
        try {
            System.out.println("m2");
        }finally {
            lock.unlock();
        }
    }
}
