package com.kenshiro.concurrent.test;

public class Test5 {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();


    public static void main(String[] args) {

        synchronized (lock1) {
            try {
                lock1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
