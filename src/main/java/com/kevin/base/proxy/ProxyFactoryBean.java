package com.kevin.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author kevin
 */
public class ProxyFactoryBean {
    private Advice advice;
    private Object target;

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{target.getClass()},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        advice.beforeMethod(method);
                        Object retVal = method.invoke(target, args);
                        advice.afterMethod(method);
                        return retVal;
                    }
                });
        return proxy;
    }

}
