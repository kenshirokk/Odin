package com.kenshiro.concurrent.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Test6 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            People people = new People();
            new Thread(people).start();
        }
        TimeUnit.SECONDS.sleep(1);
        for (Integer id : MailBoxes.getIds()) {
            new Postman(id, "内容 [" + id + "]").start();
        }

    }
}

@Slf4j
class People implements Runnable {

    @Override
    public void run() {
        //收信
        GuardedObject go = MailBoxes.createGuardedObject();
        log.debug("begin收信 id:{}", go.getId());
        Object mail = go.get(5000);
        log.debug("end收信 id:{}, 内容:{}", go.getId(), mail);

    }
}

@Slf4j
class Postman extends Thread {
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject go = MailBoxes.getGuardedObject(id);
        log.debug("送信 id:{}, 内容:{}", go.getId(), mail);
        go.complete(mail);
    }
}

class MailBoxes {
    private static AtomicInteger id = new AtomicInteger(1);
    private static Map<Integer, GuardedObject> boxes = new ConcurrentHashMap<>();

    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(id.getAndIncrement());
        boxes.put(go.getId(), go);
        return go;
    }

    public static GuardedObject getGuardedObject(int id) {
        return boxes.remove(id);
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

@Data
class GuardedObject {
    private int id;
    private Object response;

    public GuardedObject(int id) {
        this.id = id;
    }

    public Object get(long timeout) {
        synchronized (this) {
            //开始时间
            long begin = System.currentTimeMillis();
            //经历的时间
            long passedTime = 0;
            while (response == null) {
                //剩余等待时间
                long waitTime = timeout - passedTime;
                if (passedTime >= timeout) {
                    break;
                }
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}