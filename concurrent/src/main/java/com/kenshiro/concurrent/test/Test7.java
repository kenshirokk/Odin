package com.kenshiro.concurrent.test;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Test7 {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.push(new Message(id, "值"+id));
            }, "生产者" + i).start();
        }


        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message pop = queue.pop();
            }
        }).start();

        System.out.println(123);
    }
}

/**
 * 消息队列类  java线程之间通信
 */
@Slf4j
class MessageQueue {

    private LinkedList<Message> list = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public void push(Message message) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    log.debug("队列满了, 生产者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("已生产消息{}", message);
            list.addLast(message);
            list.notifyAll();
        }
    }

    public Message pop() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空, 消费者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.notifyAll();
            log.debug("已消费消息");
            return list.removeFirst();
        }
    }

}

@Getter
@ToString
final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }
}