package com.kevin.base.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 数组阻塞队列
 *
 * @author kevin
 */
public class BlockingQueueCommunication {
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

        System.out.println("Integer = " + Integer.MAX_VALUE + Integer.MIN_VALUE);
    }

    static class Business {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

        /**
         * 匿名代码块(匿名构造方法),执行顺序优先于所有构造方法，每创建一次实例对象时都会执行一次
         *
         * 静态代码块，是在类加载的时候调用，只执行一次
         */ {
            try {
                queue2.put(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sub(int j) {
            try {
                queue1.put(1);
                for (int i = 0; i < 100; i++) {
                    System.out.println("sub Thread sequence of :" + j + " do loop:" + i);
                }
                queue2.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void main(int j) {
            try {
                queue2.put(1);
                for (int i = 0; i < 10; i++) {
                    System.out.println("main Thread sequence of :" + j + " do loop:" + i);
                }
                queue1.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
