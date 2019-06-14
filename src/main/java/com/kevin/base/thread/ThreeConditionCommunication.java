package com.kevin.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kevin
 */
public class ThreeConditionCommunication {

    public static void main(String[] args) {
        final Communication communication = new Communication();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    communication.firstDloop(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    communication.secondDloop(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    communication.threeDloop(i);
                }
            }
        }).start();
    }

}

class Communication {
    final Lock lock = new ReentrantLock();
    final Condition condition1 = lock.newCondition();
    final Condition condition2 = lock.newCondition();
    final Condition condition3 = lock.newCondition();
    int count = 1;

    public void firstDloop(int m) {
        lock.lock();
        try {
            while (count != 1) {
                condition1.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("firstDloop Thread sequence of :" + m + " do loop:" + i);
            }
            count = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void secondDloop(int j) {
        lock.lock();
        try {
            while (count != 2) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("secondDloop Thread sequence of :" + j + " do loop:" + i);
            }
            count = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void threeDloop(int k) {
        lock.lock();
        try {
            while (count != 3) {
                condition3.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("threeDloop Thread sequence of :" + k + " do loop:" + i);
            }
            count = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}