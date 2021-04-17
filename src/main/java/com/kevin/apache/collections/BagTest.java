package com.kevin.apache.collections;

import com.kevin.base.BaseApp;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

/**
 * Bag - Bag界面简化了每个对象具有多个副本的集合。
 * Bag定义了一个集合，用于计算对象在集合中出现的次数。
 *
 * @ClassName BagTest
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 17:32
 */
public class BagTest extends BaseApp {

    public static void main(String[] args) {
        Bag<String> bag = new HashBag<>();
        bag.add("a" , 2);
        bag.add("b");
        bag.add("c");
        bag.add("d",3);

        // 获取次数
        System.out.println("d is present " + bag.getCount("d") + " times.");
        System.out.println("bag: " +bag);
        System.out.println("Unique Set: " +bag.uniqueSet());

        println("", true);

        // 删除 2次
        bag.remove("d",2);
        System.out.println("2 occurences of d removed from bag: " +bag);
        System.out.println("d is present " + bag.getCount("d") + " times.");
        System.out.println("bag: " +bag);
        System.out.println("Unique Set: " +bag.uniqueSet());
    }
}
