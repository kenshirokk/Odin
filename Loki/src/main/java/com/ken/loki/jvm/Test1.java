package com.kenshiro.concurrent.jvm;

import com.kenshiro.concurrent.unit.Sleeper;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(new Student());
        }

        Sleeper.sleep(1000000);
    }

    public static void m1() {
        m1();
    }
}

class Student {
    private byte[] h = new byte[1024 * 1024];
}
