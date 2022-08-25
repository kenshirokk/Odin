package com.kenshiro.concurrent.jvm;

public class Test12 {

    public static void main(String[] args) {
        int result = test();
        System.out.println(result);
    }

    public static int test() {
        try {
            int s = 1 / 0;
            return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
        } finally {
//            return 2;
        }
        return 3;
    }
}
