package com.kenshiro.concurrent.n8;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test1 {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000,
                TimeUnit.MILLISECONDS, 1,
                (queue, task)->{
                    //1 死等
//                    queue.put(task);//死等
                    //2 带超时时间的等待
//                    queue.offer(task, 1500, TimeUnit.MILLISECONDS);
                    //3 放弃任务执行
//                    log.debug("什么都不做 放弃{} ", task);
                    //4 抛出异常
//                    throw new RuntimeException("任务执行失败" + task);
                    //5 让调用者自己执行任务
                    task.run();
                });
        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() -> {
                Sleeper.sleep(1);
                log.debug(""+j);
            });
        }
    }
}

@FunctionalInterface    //拒绝策略
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j
class ThreadPool {
    //1 任务队列
    private BlockingQueue<Runnable> taskQueue;

    //2 线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //3 核心线程数
    private int coreSize;

    //4 获取任务的超时时间
    private long timeout;
    private TimeUnit timeUnit;

    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    //执行任务
    public void execute(Runnable task) {
        //当前任务没有超过核心数  直接交给worker执行
        //如果任务超过核心  加入队列
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker {}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
//                log.debug("加入任务队列 {}", task);
//                taskQueue.put(task);
                //1 死等
                //2 带超时时间的等待
                //3 放弃任务执行
                //4 抛出异常
                //5 让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //1 如果task不为空, 执行任务
            //2 当task执行完毕 从队列取任务继续执行
//            while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.debug("正在执行...{}", task);
                    task.run();
                } catch (Exception e) {
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("worker 被移除..{}", this);
                workers.remove(this);
            }
        }
    }

}

@Slf4j(topic = "k.BlockingQueue")
class BlockingQueue<T> {
    //1 任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //2 锁
    private ReentrantLock lock = new ReentrantLock();

    //3 生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4 消费者消费变量
    private Condition emptyWaitSet = lock.newCondition();

    //5 容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //6 阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //7 阻塞添加
    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.debug("等待加入任务队列 {}", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列 {}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //带超时时间的阻塞添加
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    log.debug("等待加入任务队列 {}", task);
                    //返回剩余时间
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列 {}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    //8 获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    //带超时时间的获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    //返回剩余时间
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (queue.size() == capacity) {
                //满了
                rejectPolicy.reject(this, task);
            } else { //有空间 没满
                log.debug("加入任务队列 {}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
