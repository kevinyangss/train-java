package com.kevin.base.thread;

/**
 * 多线程共享数据
 *
 * @author kevin.yang
 */
public class MulitThreadShareData {
    private static ShareData2 sd3 = new ShareData2();

    public static void main(String[] args) {
        /**
         * 如果每个线程执行的代码相同
         * 可以使用同一个Runnable对象，Runnable对象中有那个共享数据
         * 例如：卖票系统
         */
        ShareData1 sd1 = new ShareData1();
        new Thread(sd1).start();

        /**
         * 如果每个线程执行的代码不同
         * 可以将共享数据封装在一个对象中，然后将这个对象逐一传递给各个Runnable对象
         */
        ShareData2 sd2 = new ShareData2();
        new Thread(new MyRunnable1(sd2)).start();
        new Thread(new MyRunnable2(sd2)).start();

        /**
         * 如果每个线程执行的代码不同
         * 将这些Runnable对象作为某一个类中的内部类，共享数据作为这个外部类中的成员变量，每个线程对共享数据的操作方法也分配给外部类
         * private static ShareData2 sd3 = new ShareData2();
         * 或者
         * final ShareData2 sd3 = new ShareData2();
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                sd3.increment();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sd3.decrement();
            }
        }).start();
    }
}

class ShareData1 implements Runnable {
    private int count = 10;

    @Override
    public void run() {
        while (true) {
            count--;
            if (count <= 1) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "当前剩余票数为：" + count);
        }
    }
}

class ShareData2 {
    private int i = 0;

    public synchronized void increment() {
        i++;
        System.out.println(Thread.currentThread().getName() + "当前值为：" + i);
    }

    public synchronized void decrement() {
        i--;
        System.out.println(Thread.currentThread().getName() + "当前值为：" + i);
    }
}

class MyRunnable1 implements Runnable {
    private ShareData2 sd2;

    public MyRunnable1(ShareData2 sd2) {
        this.sd2 = sd2;
    }

    @Override
    public void run() {
        sd2.increment();
    }

}

class MyRunnable2 implements Runnable {
    private ShareData2 sd2;

    public MyRunnable2(ShareData2 sd2) {
        this.sd2 = sd2;
    }

    @Override
    public void run() {
        sd2.decrement();
    }

}