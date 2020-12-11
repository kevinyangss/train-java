package com.kevin.base.reflect;

import java.lang.reflect.Method;

/**
 * @author kevin.yang
 */
public class MethodReflect {
    public static void main(String[] args) throws Exception {
        String str1 = "yes";
        Method methodCharAt = String.class.getMethod("charAt", int.class);
        System.out.println(methodCharAt.invoke(str1, 1));
        //如果调用的时候对象传的是null,则是调用的静态方法
        //System.out.println(methodCharAt.invoke(null, 1));

        // 利用反射运行其他代码的main方法
        Method mainMethod = Class.forName("com.kevin.base.reflect.TestArguments").getMethod("main", String[].class);
        mainMethod.invoke(null, new Object[]{new String[]{"111", "222"}});
    }

}

class TestArguments {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
