package com.kevin.base.thread;

/**
 * 传统线程
 *
 * @author kevin.yang
 */
public class TraditionalThread {
    public static void main(String[] args) throws Exception {
        //第一个线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                    System.out.println("2:" + this.getName());
                }
            }
        };
        thread.start();

        //第二个线程
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3:" + Thread.currentThread().getName());
                }
            }
        });
        thread2.start();
    }
}
