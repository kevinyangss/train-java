package com.kevin.apache.collections;

import com.kevin.base.BaseApp;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

/**
 * 使用双向映射，可以使用值查找键，并且可以使用键轻松查找值。
 *
 * @ClassName BidiMapTest
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 17:44
 */
public class BidiMapTest extends BaseApp {

    public static void main(String[] args) {
        BidiMap<String, String> bidi = new TreeBidiMap<>();
        bidi.put("One", "1");
        bidi.put("Two", "2");
        bidi.put("Three", "3");

        // 根据key查找值
        System.out.println(bidi.get("One"));
        // 根据值查找 key
        System.out.println(bidi.getKey("1"));
        System.out.println("Original Map: " + bidi);

        println("", true);

        // 根据值删除
        bidi.removeValue("1");
        System.out.println("Modified Map: " + bidi);

        println("", true);

        // 倒排
        BidiMap<String, String> inversedMap = bidi.inverseBidiMap();
        System.out.println("Inversed Map: " + inversedMap);
    }
}
