package com.kenshiro.concurrent.n8;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestCyclicBarrier {

    public static void main(String[] args) {
//        ExecutorService exe = Executors.newFixedThreadPool(3);
        ExecutorService exe = Executors.newCachedThreadPool();

        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            log.debug("finished");
        });

        for (int i = 0; i < 3; i++) {
            exe.submit(() -> {
                log.debug("task1 begin");
                Sleeper.sleep(1);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            exe.submit(() -> {
                log.debug("task2 begin");
                Sleeper.sleep(1);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
