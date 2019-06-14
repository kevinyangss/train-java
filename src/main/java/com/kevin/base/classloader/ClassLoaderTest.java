package com.kevin.base.classloader;

/**
 * @author kevin
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName());
        // 类加载器为null,不代表没有类加载器，它的类加载器为BootStrap,C++的二进制
        System.out.println(System.class.getClassLoader());

        /**
         * 类加载器树状结构BootStrap/ExtClassLoader/AppClassLoader
         * BootStrap	JRE/lib/rt.jar
         * ExtClassLoader	JRE/lib/ext/*.jar
         * AppClassLoader	CLASSPATH指定的所有jar或目录
         */
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();
        }
        System.out.println(loader);
    }

}
