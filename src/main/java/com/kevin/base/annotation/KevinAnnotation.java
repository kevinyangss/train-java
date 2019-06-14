package com.kevin.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
// 注释保留的生命周期source,class,runtime
@Retention(RetentionPolicy.RUNTIME)
// 定义注释的作用范围，方法，类，接口等.数组类型的value
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface KevinAnnotation {
    //为注解添加属性,并提供缺省值
    String color() default "blue";

    String value();
    //枚举

    //注解属性的值还是注解，给默认值
    MetaAnnotation annotationAttr() default @MetaAnnotation("meta");

}
