package com.kevin.base.reflect;

import com.kevin.base.BaseApp;

import java.lang.reflect.Constructor;

/**
 * 构造方法的反射
 *
 * @author kevin.yang
 */
public class ConstructorReflect extends BaseApp {
    public static void main(String[] args) throws Exception {
        Constructor[] constructors = String.class.getConstructors();
        for (Constructor constructor : constructors) {
            println(constructor.getDeclaringClass().getName(), false);
        }

        //获取什么类型的构造方法
        Constructor constructor = String.class.getConstructor(StringBuffer.class);
        //要用到和上面相同类型的实例对象
        String s = (String) constructor.newInstance(new StringBuffer("abc"));
        println(s, false);
    }

}
