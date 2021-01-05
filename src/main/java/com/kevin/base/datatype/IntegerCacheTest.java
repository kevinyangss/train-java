package com.kevin.base.datatype;

import com.kevin.base.BaseApp;

import java.lang.reflect.Field;

/**
 * @ClassName IntegerCacheTest
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-01-05 17:26
 */
public class IntegerCacheTest extends BaseApp {

    public static void main(String[] args) throws Exception {
        // 正常操作
        Integer a = 100, b = 100;
        println(String.format("a == b : %s", a == b), false);
        Integer c = 1000, d = 1000;
        println(String.format("c == d : %s", c == d), false);

        
        // 魔法一
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);
        // 通过反射获取到Integer中cache了的对象集合
        Integer[] newCache = (Integer[]) myCache.get(cache);
        // 因为默认从-128缓存到127，所以下标为133对应的就是5，132对应的是4
        newCache[132] = newCache[133];// 5
        int e = 2;
        int f = e + e;
        println(String.format("%d + %d = %d", e, e, f), false);

        // 魔法二、通过设置JVM参数：-XX:AutoBoxCacheMax=1000，就可以让两个1000的Integer对象==为true了
        // 在test的VM options：-XX:AutoBoxCacheMax=1000

    }
}
