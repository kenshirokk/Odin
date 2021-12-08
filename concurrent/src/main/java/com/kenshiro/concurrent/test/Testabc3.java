package com.kenshiro.concurrent.test;

import java.util.concurrent.locks.LockSupport;

public class Testabc3 {
    static Thread a;
    static Thread b;
    static Thread c;
    public static void main(String[] args) {

        ParkUnpark p = new ParkUnpark(5);
        a = new Thread(() -> {
            p.print("a", b, false);
        },"a");

        b = new Thread(() -> {
            p.print("b", c, false);
        },"b");

        c = new Thread(() -> {
            p.print("c", a, true);
        },"c");

        a.start();
        b.start();
        c.start();

        LockSupport.unpark(a);
    }
}

class ParkUnpark {
    private int loopNum;

    public ParkUnpark(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String str, Thread next, boolean rn) {
        for (int i = 0; i < loopNum; i++) {
            LockSupport.park();
            if (rn) {
                System.out.println(str);
            } else {
                System.out.print(str);
            }
            LockSupport.unpark(next);
        }
    }
}