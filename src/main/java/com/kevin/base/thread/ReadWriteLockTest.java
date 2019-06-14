package com.kevin.base.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kevin
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        myQueue.get();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        myQueue.put(new Random().nextInt(10000));
                    }
                }
            }).start();
        }

    }

}

class MyQueue {
    //共享数据
    private Object data = null;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to read data!");
            Thread.sleep((long) Math.random() * 1000);
            System.out.println(Thread.currentThread().getName() + " have read data!" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(Object data) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to write data!");
            Thread.sleep((long) Math.random() * 1000);
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " have write data!" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
