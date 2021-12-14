package com.kenshiro.concurrent.jvm;

public class Test2 {


    public static void main(String[] args) {
        String s1 = "a";
//        String s2 = "b";
        String s3 = "ab";
//        String s4 = s1 + s2;
        String s5 = "a" + "b";
        String s6 = s1 + "b";
//        System.out.println(s4 == s3);
        System.out.println(s5 == s3);
        System.out.println(s6 == s3);

    }
}
