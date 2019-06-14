package com.kevin.base.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * java四种引用类型:强引用、软引用、弱引用、虚引用
 * jdk 1.2之前只有强引用，1.2以后多了三个引用，软引用、弱引用、虚引用
 *
 * @author kevin
 */
public class ReferenceTest {
    public static void main(String[] args) {
        //强引用
        strongReferenceTest();
        //软引用
        sofrReferenceTest();
        //弱引用
        weakReferenceTest();
        //虚引用
        phantomReferenceTest();
    }

    /**
     * 强引用
     * 任何时候都不会被gc回收
     */
    private static void strongReferenceTest() {
        String strong = new String("strong");
        //多次回收都没有被gc回收
        System.gc();
        System.gc();
        System.out.println(strong.toString());
    }

    /**
     * 软引用
     * 只有当内存不够的时候才会回收
     * 适合一些缓存，比如Android中的图片的缓存
     */
    private static void sofrReferenceTest() {
        String soft = new String("soft");
        SoftReference<String> softReference = new SoftReference<String>(soft);
        System.out.println("gc before>>>>" + softReference.get());
        System.gc();
        System.gc();
        System.out.println("gc after>>>>" + softReference.get());
    }

    /**
     * 弱引用
     * 只要gc一运行就会回收
     */
    private static void weakReferenceTest() {
        String weak = new String("weak");
        WeakReference<String> weakReference = new WeakReference<String>(weak);
        System.out.println(weakReference.get());
        //上面是强引用，要置空才能回收
        weak = null;
        System.gc();
        System.gc();
        System.out.println(weakReference.get());
    }

    /**
     * 虚引用
     * 相当于没引用，一创建这种引用，就回收了
     */
    private static void phantomReferenceTest() {
        String phantom = new String("phantom");
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> phantomReference = new PhantomReference<String>(phantom, queue);
        System.out.println(phantomReference.get());
        //都不用自己回收
//		phantom = null;
//		System.gc();
//		System.gc();
        System.out.println(phantomReference.get());
    }
}
