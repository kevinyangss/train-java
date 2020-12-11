package com.kevin.base.proxy;

import java.lang.reflect.Method;

/**
 * @author kevin.yang
 */
public class MyAdvice implements Advice
{
	long beginTime = 0; 
	
	@Override
	public void beforeMethod(Method method)
	{
		beginTime = System.currentTimeMillis();
	}

	@Override
	public void afterMethod(Method method)
	{
		long endTime = System.currentTimeMillis();
		System.out.println(method.getName() + " runing time of " + (endTime - beginTime));
	}

}
