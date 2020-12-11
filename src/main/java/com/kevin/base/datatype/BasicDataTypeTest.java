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
        // 溢出
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
        println("i (" + i + ") + j (" + j + ") = k (" + k + ")");
    }
}
