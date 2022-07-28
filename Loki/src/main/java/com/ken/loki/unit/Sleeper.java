package com.kenshiro.concurrent.unit;

import java.util.concurrent.TimeUnit;

public class Sleeper {
    public static void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
