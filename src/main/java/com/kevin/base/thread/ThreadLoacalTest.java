package com.kevin.base.thread;

import java.util.Random;

/**
 * 线程间的变量共享
 *
 * @author kevin
 */
public class ThreadLoacalTest {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + "has put data:" + data);
                    MyThreadScopeData.getInstance().setAge(data);
                    MyThreadScopeData.getInstance().setName("name:" + data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            MyThreadScopeData threadScopeData = MyThreadScopeData.getInstance();
            System.out.println("A from " + Thread.currentThread().getName() + "get data:" + threadScopeData.getName() + ",age=" + threadScopeData.getAge());
        }
    }

    static class B {
        public void get() {
            MyThreadScopeData threadScopeData = MyThreadScopeData.getInstance();
            System.out.println("B from " + Thread.currentThread().getName() + "get data:" + threadScopeData.getName() + ",age=" + threadScopeData.getAge());
        }
    }
}

class MyThreadScopeData {
    private MyThreadScopeData() {
    }

    public static MyThreadScopeData getInstance() {
        MyThreadScopeData instance = map.get();
        if (null == instance) {
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }

    //线程共享数据
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}