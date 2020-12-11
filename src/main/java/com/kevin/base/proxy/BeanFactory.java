package com.kevin.base.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author kevin.yang
 */
public class BeanFactory {
    Properties props = new Properties();

    public BeanFactory(InputStream ips) {
        try {
            props.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) {
        Object bean = null;
        String className = props.getProperty(name);
        try {
            Class clazz = Class.forName(className);
            bean = clazz.newInstance();
            if (bean instanceof ProxyFactoryBean) {
                ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
                Advice advice = (Advice) Class.forName(props.getProperty(name + ".advice")).newInstance();
                Object target = Class.forName(props.getProperty(name + ".target")).newInstance();
                proxyFactoryBean.setAdvice(advice);
                proxyFactoryBean.setTarget(target);
                Object proxy = proxyFactoryBean.getProxy();
                return proxy;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
