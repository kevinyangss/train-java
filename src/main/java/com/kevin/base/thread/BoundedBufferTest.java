package com.kevin.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞缓冲
 *
 * @author yangshuaishuai
 */
public class BoundedBufferTest {

    public static void main(String[] args) {
        final BoundedBuffer bb = new BoundedBuffer();
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " take data value is :" + bb.take());
                }
            }).start();
        }

        for (int i = 0; i < 200; i++) {
            final int x = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bb.put(x);
                    System.out.println(Thread.currentThread().getName() + " put data of :" + x);
                }
            }).start();
        }
    }

}

class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[10];
    int putptr, takeptr, count;

    public void put(Object x) {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            System.out.println(Thread.currentThread().getName() + " currentThread put items nums of :" + count);
            notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        Object x = null;
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            System.out.println(Thread.currentThread().getName() + " currentThread take items nums of :" + count);
            notFull.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return x;
    }
}
