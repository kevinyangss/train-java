package com.kevin.base.proxy;

import java.lang.reflect.Method;

/**
 * @author kevin
 */
public interface Advice {
    void beforeMethod(Method method);

    void afterMethod(Method method);
}
