package com.kevin.base.annotation;

/**
 * java注解
 * @author kevin
 */
@KevinAnnotation(color = "red", value = "abc", annotationAttr = @MetaAnnotation("kevin"))
public class AnnotationTest {
    /**
     * 抑制过时方法的警告
     */
    @SuppressWarnings("deprecation")
    @KevinAnnotation(value = "kevin")
    public static void main(String[] args) {
        System.runFinalizersOnExit(true);
        sayHello();
        if (AnnotationTest.class.isAnnotationPresent(KevinAnnotation.class)) {
            KevinAnnotation annotation = (KevinAnnotation) AnnotationTest.class.getAnnotation(KevinAnnotation.class);
            //调用注解的属性，还是方法形式调用
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            System.out.println(annotation.annotationAttr());
        }
    }

    /**
     * 让一个方法过时
     */
    @Deprecated
    private static void sayHello() {
        System.out.println("Hi,World!");
    }
}
