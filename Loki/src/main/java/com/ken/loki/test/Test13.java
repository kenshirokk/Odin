package com.kenshiro.concurrent.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 牛逼的例子
 */
public class Test13 {
    public static void main(String[] args) {
        demo(() -> new int[10],
                (array) -> array.length,
                (array, index) -> array[index]++,
                array -> System.out.println(Arrays.toString(array)));

        demo(() -> new AtomicIntegerArray(10),
                array -> array.length(),
                (array, index) -> array.getAndIncrement(index),
                System.out::println);
    }

    /**
     * supplier ()-> 无中生有
     * function (arg)->return       BiFunction (arg1, arg2) -> return
     * consumer (arg)->void         BiConsumer (arg1, arg2) -> void
     */
    private static <T> void demo(
            Supplier<T> arraySupplier,
            Function<T, Integer> lengthFun,
            BiConsumer<T, Integer> putConsumer,
            Consumer<T> print) {
        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        int length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % length);
                }
            }));
        }
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        print.accept(array);
    }
}
