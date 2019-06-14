package com.kevin.base.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 泛型
 * @author kevin
 */
public class GenericTest {
    public static void main(String[] args) throws Exception {
        ArrayList collection1 = new ArrayList();
        collection1.add(1);
        collection1.add("ss");

        //泛型是给编译器看的
        ArrayList<Integer> collection2 = new ArrayList<Integer>();
        //编译完成后就没有了泛型的类型信息
        collection2.getClass().getMethod("add", Object.class).invoke(collection2, "abc");
        System.out.println(collection2.get(0));

        printCollection(collection2);

        HashMap<String, Integer> maps = new HashMap<String, Integer>();
        maps.put("xx", 19);
        maps.put("zz", 33);
        maps.put("aa", 13);
        Set<Map.Entry<String, Integer>> entrySet = maps.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println(autoConvert("abs"));

        //通过反射获得泛型的实际类型参数
        Method applyMethod = GenericTest.class.getMethod("applyVector", Vector.class);
        Type[] types = applyMethod.getGenericParameterTypes();
        ParameterizedType pType = (ParameterizedType) types[0];
        System.out.println(pType.getRawType());
        System.out.println(pType.getTypeName());
        System.out.println(pType.getActualTypeArguments()[0]);
        System.out.println(pType.getOwnerType());
    }

    /**
     * 泛型通配符?
     * 可以引用其他各种参数化的类型，可以调用与参数化无关的方法，不能调用与参数化有关的方法
     * 限定通配符的上边界：
     * 正确：Vector<? extends Number> x = new Vector<Integer>();
     * 错误：Vector<? extends Number> x = new Vector<String>();
     * 限定通配符的下边界：
     * 正确：Vector<? super Integer> x = new Vector<Number>();
     * 错误：Vector<? super Integer> x = new Vector<Byte>();
     */
    private static void printCollection(Collection<?> collection) {
        for (Object obj : collection) {
            System.out.println(obj);
        }
        System.out.println(collection.size());
    }

    /**
     * 自定义泛型
     */
    private static <T> T autoConvert(Object obj) {
        return (T) obj;
    }

    public static void applyVector(Vector<Date> t) {

    }
}
