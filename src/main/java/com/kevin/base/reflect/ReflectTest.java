package com.kevin.base.reflect;

/**
 * 反射就是把java类中的各种成分映射成相应的java类
 *
 * @author kevin
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        /**
         * 获取一个类的字节码有三种方式
         * new String().getClass();
         * String.class;
         * Class.forName("java.lang.String");
         */
        String str1 = "yss";
        Class cls1 = str1.getClass();
        Class cls2 = String.class;
        Class cls3 = Class.forName("java.lang.String");
        System.out.println(cls1 == cls2);
        System.out.println(cls1 == cls3);

        //字节码是不是基本类型的
        System.out.println(cls1.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(int.class == Integer.class);
        System.out.println(int.class == Integer.TYPE);
        System.out.println(int[].class.isPrimitive());
        System.out.println(int[].class.isArray());
        System.out.println(void.class.isPrimitive());
    }
}
