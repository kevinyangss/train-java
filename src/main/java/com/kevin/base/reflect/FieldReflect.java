package com.kevin.base.reflect;

import java.lang.reflect.Field;

/**
 * 字段的反射
 *
 * @author kevin.yang
 */
public class FieldReflect {
    public static void main(String[] args) throws Exception {
        ReflectPoint rp1 = new ReflectPoint(3, 5);
        Field fieldY = rp1.getClass().getField("y");
        // fieldY不是对象身上的变量，而是类上的，要用它去对象身上取
        System.out.println(fieldY.get(rp1));

        // 访问私有的变量，暴力反射
        Field fieldX = rp1.getClass().getDeclaredField("x");
        fieldX.setAccessible(true);
        System.out.println(fieldX.get(rp1));

        changeStringValue(rp1);
        System.out.println(rp1);
    }

    private static void changeStringValue(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field name is =" + field.getName());
            //比较字节码用==
            if (field.getType() == String.class) {
                String oldValue = (String) field.get(obj);
                String newValue = oldValue.replace("b", "a");
                field.set(obj, newValue);
            }
        }
    }

}
