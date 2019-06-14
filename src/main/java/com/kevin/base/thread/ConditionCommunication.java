package com.kevin.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kevin
 */
public class ConditionCommunication {

    public static void main(String[] args) {
        final Business printLner = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    printLner.sub(i);
                }
            }
        }).start();

        for (int j = 1; j <= 50; j++) {
            printLner.main(j);
        }
    }

}

class Business {
    private boolean bShouldSub = true;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void sub(int j) {
        lock.lock();
        try {
            while (!bShouldSub) {
                try {
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("sub Thread sequence of :" + j + " do loop:" + i);
            }
            bShouldSub = false;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void main(int j) {
        lock.lock();
        try {
            while (bShouldSub) {
                try {
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("main Thread sequence of :" + j + " do loop:" + i);
            }
            bShouldSub = true;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}