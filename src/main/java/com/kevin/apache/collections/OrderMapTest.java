package com.kevin.apache.collections;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

/**
 * OrderedMap是地图的新接口，用于保留添加元素的顺序。
 * LinkedMap和ListOrderedMap是两个可用的实现。 此接口支持Map的迭代器，并允许在Map中向前或向后迭代两个方向。
 *
 * @ClassName OrderMapTest
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 17:56
 */
public class OrderMapTest {

    public static void main(String[] args) {
        OrderedMap<String, String> map = new LinkedMap<String, String>();
        map.put("One", "1");
        map.put("Two", "2");
        map.put("Three", "3");
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());

        System.out.println(map.nextKey("One"));
        System.out.println(map.nextKey("Two"));

        System.out.println(map.previousKey("Three"));

        System.out.println(map.values());
    }
}
