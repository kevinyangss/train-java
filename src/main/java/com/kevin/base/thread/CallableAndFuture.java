package com.kevin.base.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 得到线程运行结果
 *
 * @author kevin
 */
public class CallableAndFuture {
    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(
                new Callable<String>() {
                    public String call() throws InterruptedException {
                        Thread.sleep(2000);
                        return "hello";
                    }
                }
        );
        System.out.println("等待结果");
        if (null != future.get()) {
            System.out.println("我在等待");
        }
        Thread.sleep(1000);
        System.out.println("得到结果" + future.get());
        threadPool.shutdown();

        /**
         * 向线程提交多个任务，获取线程执行的返回结果
         */
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 1; i <= 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(completionService.take().get());
        }
        threadPool2.shutdown();
    }

}
