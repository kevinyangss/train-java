package com.kevin.base.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时计数器
 *
 * @author kevin.yang
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "正准备接受命令");
                        cdOrder.await();
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "已接受命令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "回应命令处理结果");
                        cdAnswer.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程" + Thread.currentThread().getName() +
                    "即将发布命令");
            cdOrder.countDown();
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            cdAnswer.await();
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到所有响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
