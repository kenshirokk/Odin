package com.kenshiro.concurrent.n8;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test2 {

    public static void main(String[] args) {
        DataContainer dc = new DataContainer();
        new Thread(() -> {
            dc.read();
        }, "t1").start();
        new Thread(() -> {
            dc.read();
        }, "t2").start();
    }
}

@Slf4j
class DataContainer {
    private Object data;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = lock.readLock();
    private ReentrantReadWriteLock.WriteLock w = lock.writeLock();

    public Object read() {
        r.lock();
        log.debug("获得读锁");
        try {
            log.debug("read...");
            Sleeper.sleep(2);
            return data;
        } finally {
            r.unlock();
            log.debug("释放读锁");
        }
    }

    public void write(Object data) {
        w.lock();
        log.debug("获得写锁");
        try {
            log.debug("write...");
        } finally {
            w.unlock();
            log.debug("释放写锁");
        }
    }
}