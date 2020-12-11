package com.kevin.base.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author kevin.yang
 */
public class ProxyTest {
    public static void main(String[] args) throws Exception {
        /**
         * 生成的类中有哪些方法，通过让其实现哪些接口的方式进行告知
         * 产生的类字节码必须有一个关联的类加载器对象
         */
        Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

        Constructor[] constructors = clazzProxy1.getConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            System.out.println(name);
        }

        //用反射获得构造方法
        Constructor constructor = clazzProxy1.getConstructor(InvocationHandler.class);
        //调用构造方法创建动态类的实例对象，并将编写的Invocationhandler类的实例对象传进去
        Collection proxy = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        Collection proxy2 = (Collection) Proxy.newProxyInstance(
                Collection.class.getClassLoader(),
                new Class[]{Collection.class},
                new InvocationHandler() {
                    ArrayList target = new ArrayList();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        long beginTime = System.currentTimeMillis();
                        Object retVal = method.invoke(target, args);
                        long endTime = System.currentTimeMillis();
                        System.out.println(proxy.getClass().getName() + method.getName() + " runing time of " + (endTime - beginTime));
                        return retVal;
                    }
                });
        proxy2.add("yss");
        proxy2.add("y");
        proxy2.add("ys");
        proxy2.add("ysss");
        System.out.println(proxy2.size());
    }

}
