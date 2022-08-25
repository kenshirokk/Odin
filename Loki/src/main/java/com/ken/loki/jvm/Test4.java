package com.kenshiro.concurrent.jvm;

import java.util.ArrayList;
import java.util.List;

public class Test4 {

    public static final int _512KB = 512 * 1024;
    public static final int _1MB = 1024 * 1024;
    public static final int _6MB = 6 * 1024 * 1024;
    public static final int _7MB = 7 * 1024 * 1024;
    public static final int _8MB = 8 * 1024 * 1024;

    //-Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -Xlog:gc* -verbose:gc
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        /*list.add(new byte[_7MB]);
        list.add(new byte[_512KB]);
        list.add(new byte[_512KB]);*/
        list.add(new byte[_8MB]);
        list.add(new byte[_8MB]);
    }
}
