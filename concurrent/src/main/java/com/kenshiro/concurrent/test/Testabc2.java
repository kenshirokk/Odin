package com.kenshiro.concurrent.test;

import com.kenshiro.concurrent.unit.Sleeper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Testabc2 {

    public static void main(String[] args) {
        AwaitSignal as = new AwaitSignal(5);
        Condition a = as.newCondition();
        Condition b = as.newCondition();
        Condition c = as.newCondition();
        Condition d = as.newCondition();

        new Thread(() -> {
            as.print("a", a, b, false);
        }, "a").start();
        new Thread(() -> {
            as.print("b", b, c, false);
        }, "b").start();
        new Thread(() -> {
            as.print("c", c, d, false);
        }, "c").start();
        new Thread(() -> {
            as.print("d", d, a, true);
        }, "d").start();

        Sleeper.sleep(1);
        as.lock();
        try {
            a.signal();
        } finally {
            as.unlock();
        }
    }
}

class AwaitSignal extends ReentrantLock {
    private int loopNum;

    public AwaitSignal(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String str, Condition current, Condition next, boolean rn) {
        for (int i = 0; i < loopNum; i++) {
            lock();
            try {
                try {
                    current.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (rn) {
                    System.out.println(str);
                } else {
                    System.out.print(str);
                }
                next.signal();
            } finally {
                unlock();
            }
        }
    }
}
