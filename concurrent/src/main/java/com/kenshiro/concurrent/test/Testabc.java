package com.kenshiro.concurrent.test;

public class Testabc {

    public static void main(String[] args) {
//        abc();
        WaitNotify w = new WaitNotify(1, 5);
        new Thread(() -> {
            w.print("a", 1, 2, false);
        },"a").start();
        new Thread(() -> {
            w.print("b", 2, 3, false);
        },"b").start();
        new Thread(() -> {
            w.print("c", 3, 4, false);
        },"c").start();
        new Thread(() -> {
            w.print("d", 4, 5, false);
        },"d").start();
        new Thread(() -> {
            w.print("e", 5, 1, true);
        },"e").start();
    }

    private static void abc() {
        Object lock = new Object();
        final int[] num = {1};
        Thread a = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (num[0] != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("a");
                    num[0] = 2;
                    lock.notifyAll();
                }
            }
        }, "a");

        Thread b = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (num[0] != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("b");
                    num[0] = 3;
                    lock.notifyAll();
                }
            }
        }, "b");

        Thread c = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (num[0] != 3) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("c");
                    num[0] = 1;
                    lock.notifyAll();
                }
            }
        }, "c");


        a.start();
        b.start();
        c.start();
    }
}

class WaitNotify {
    private int flag;
    private int loopNum;

    public WaitNotify(int flag, int loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
    }

    public void print(String str, int waitFlag, int nextFlag, boolean rn) {
        for (int i = 0; i < loopNum; i++) {
            synchronized (this) {
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (rn) {
                    System.out.println(str);
                } else {
                    System.out.print(str);
                }
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}