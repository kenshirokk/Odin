package com.kenshiro.concurrent.jvm;

public class Test3 {

    public static void main(String[] args) {

        String s = new String("a") + new String("b");

        String x = "ab";
        String in = s.intern();
        System.out.println(s == "ab");
        System.out.println(in == "ab");
    }
}
