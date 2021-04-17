package com.kevin.apache.collections;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;

/**
 * JDK Map接口很难迭代，因为迭代要在EntrySet或KeySet对象上完成。 MapIterator提供了对Map的简单迭代。
 *
 * @ClassName MapIteratorTest
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 17:53
 */
public class MapIteratorTest {

    public static void main(String[] args) {
        IterableMap<String, String> map = new HashedMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("4", "Four");
        map.put("5", "Five");

        MapIterator<String, String> iterator = map.mapIterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = iterator.getValue();
            System.out.println("key: " + key);
            System.out.println("Value: " + value);

            // 重新赋值
            iterator.setValue(value + "_");
        }
        System.out.println(map);
    }
}
