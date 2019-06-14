package com.kevin.base.proxy;

import java.io.InputStream;

/**
 * @author kevin
 */
public class AopFrameworkTest {

    public static void main(String[] args) {
        InputStream ips = AopFrameworkTest.class.getResourceAsStream("config.properties");
        Object bean = new BeanFactory(ips).getBean("xxx");
        System.out.println(bean.getClass().getName());
    }

}
