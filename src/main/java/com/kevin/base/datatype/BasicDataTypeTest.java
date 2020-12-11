package com.kevin.base.datatype;

import com.kevin.base.BaseApp;

/**
 * @ClassName BasicDataTypeTest
 * @Description 基本数据类型测试
 * @Author kevin
 * @Date 2020-12-11 20:11
 */
public class BasicDataTypeTest extends BaseApp {

    public static void main(String[] args) {
        // 值比较
        println("equals", true);
        equals();

        // 溢出
        println("overflow", true);
        overFlow();
    }

    /**
     * 基本数据类型溢出
     */
    private static void overFlow(){
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;

        int k = i + j;
        // k = -2,这就是发生了溢出，溢出的时候并不会抛异常，也没有任何提示。所以，使用同类型的数据进行运算的时候，一定要注意数据溢出的问题。
        println("i (" + i + ") + j (" + j + ") = k (" + k + ")", false);
    }

    private static void equals(){
        int i = 10;
        Integer j = 10;
        println("i (" + i + ") + j (" + j + "), i == j (" + (i == j) + ")", false);

        // 如果==两边都是装箱类型，则比较引用是否指向堆内存中的同一个对象.
        // Integer类只对-128~127之间的对象做了缓存
        println("(Integer)127 == (Integer)127 : " + ((Integer)127 == (Integer)127), false);
        println("(Integer)128 == (Integer)128 : " + ((Integer)128 == (Integer)128), false);
        // 如过==两边有一边是装箱类型，另外一边是基本类型，则把装箱类型拆箱为基本类型，然后进行比较。
        println("(Integer)128 == 128 : " + ((Integer)128 == 128), false);
    }
}
