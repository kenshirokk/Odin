package com.kenshiro.concurrent.test;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Test14 {

    public static void main(String[] args) {
        Student stu = new Student();

        AtomicReferenceFieldUpdater<Student, String> updater = AtomicReferenceFieldUpdater.newUpdater(Student.class,
                String.class, "name");

        System.out.println(updater.compareAndSet(stu, null, "zhangsan"));
        System.out.println(stu);
    }
}

class Student {
    volatile String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
