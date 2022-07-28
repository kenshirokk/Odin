package com.kenshiro.concurrent.test;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test11 {

    static boolean stop = false;
    static  int i = 0;
    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
//                int s = i;
                if (stop) {
                    break;
                }
            }
        }).start();

        System.out.println("stop");
        Sleeper.sleep(1);
        new Thread(() -> {
            stop = true;
//            i = 3;
        }).start();
    }
}
