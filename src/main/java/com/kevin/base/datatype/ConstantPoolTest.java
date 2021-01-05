package com.kevin.base.datatype;

import com.kevin.base.BaseApp;

/**
 * @ClassName ConstantPoolTest
 * @Description 常量池
 * @Author kevin.yang
 * @Date 2020-12-15 20:06
 */
public class ConstantPoolTest extends BaseApp {

    public static void main(String[] args) {
        // 字符串的常量池
        println("string constant pool", true);
        stringTest();

    }

    /**
     * 1、字符串的不可变性
     * 2、字符串常量池:在JVM中，为了减少相同字符串的重复创建，达到节省内存的目的。会单独开辟一块内存，用于保存字符串常量，这个内存区域被叫做字符串常量池。
     *  当代码中出现双引号形式（字面量）创建字符串对象时，JVM 会先对这个字符串进行检查，如果字符串常量池中存在相同内容的字符串对象的引用，则将这个引用返回；
     *  否则，创建新的字符串对象，然后将这个引用放入字符串常量池，并返回该引用。
     *  这种机制，就是字符串驻留或池化。
     *
     *  字符串常量池的位置：
     *      在JDK 7以前的版本中，字符串常量池是放在永久代中的。
     *      因为按照计划，JDK会在后续的版本中通过元空间来代替永久代，所以首先在JDK 7中，将字符串常量池先从永久代中移出，暂时放到了堆内存中。
     *      在JDK 8中，彻底移除了永久代，使用元空间替代了永久代，于是字符串常量池再次从堆内存移动到永久代中
     */
    private static void stringTest(){
        // 创建string方式一
        String s1 = "abc";
        // 创建string方式二
        String s2 = new String("abc");
        println(String.format("s1 == s2: %s", (s1 == s2)), false);
    }

}
