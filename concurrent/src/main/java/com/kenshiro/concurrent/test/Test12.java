package com.kenshiro.concurrent.test;

public class Test12 {

    static int x;
    static int y;
    public static void main(String[] args) {

        new Thread(() -> {
            y = 10;
            x = 20;
        }).start();

        new Thread(() -> {
            System.out.println(x);
        }).start();
    }
}
