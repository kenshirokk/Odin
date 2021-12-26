package com.kenshiro.concurrent.jvm;

public class TestAnonymousInnerClass {

    public static void main(String[] args) {
        call1(() -> System.out.println("叫你妈叫啊"));

        call2(new People() {
            @Override
            void say() {
                System.out.println("你说个屁啊");
            }
        });
    }

    public static void call1(Shoot s) {
        s.shoot();
    }

    public static void call2(People p) {
        p.say();
    }
}

@FunctionalInterface
interface Shoot {
    void shoot();
}

abstract class People {
    abstract void say();
}

