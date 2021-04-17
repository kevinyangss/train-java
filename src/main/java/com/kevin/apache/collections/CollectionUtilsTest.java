package com.kevin.apache.collections;

import com.kevin.base.BaseApp;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * CollectionUtils
 *
 * @ClassName IgnoreNullTest
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 18:01
 */
public class CollectionUtilsTest extends BaseApp {
    public static void main(String[] args) {
        println("IgnoreNull", true);
        List<String> list = new LinkedList<String>();
        CollectionUtils.addIgnoreNull(list, null);
        CollectionUtils.addIgnoreNull(list, "a");
        System.out.println(list);
        if(list.contains(null)) {
            System.out.println("Null value is present");
        } else {
            System.out.println("Null value is not present");
        }

        println("mergedList", true);
        List<String> sortedList1 = Arrays.asList("A","C","E");
        List<String> sortedList2 = Arrays.asList("B","D","F");
        List<String> mergedList = CollectionUtils.collate(sortedList1, sortedList2);
        System.out.println(mergedList);

        println("isNotEmpty", true);
        List<String> list1 = getList();
        System.out.println("Non-Empty List Check: " + checkNotEmpty1(list1));
        System.out.println("Non-Empty List Check: " + checkNotEmpty2(list1));

        println("检查列表是否是另一个列表的一部分", true);
        List<String> list3 = Arrays.asList("A","A","A","C","B","B");
        List<String> list4 = Arrays.asList("A","A","B","B");
        System.out.println("List 3: " + list3);
        System.out.println("List 4: " + list4);
        System.out.println("Is List 4 contained in List 3: " + CollectionUtils.isSubCollection(list4, list3));

        println("用于获取两个集合（交集）之间的公共对象", true);
        System.out.println("Commons Objects of List 3 and List 4: " + CollectionUtils.intersection(list3, list4));

        println("通过从其他集合中减去一个集合的对象来获取新集合", true);
        System.out.println("List 3 - List 4: " + CollectionUtils.subtract(list3, list4));

        println("用于获取两个集合的并集", true);
        System.out.println("Union of List 3 and List 4: " + CollectionUtils.union(list3, list4));
    }

    static List<String> getList() {
        return null;
    }
    static boolean checkNotEmpty1(List<String> list) {
        return !(list == null || list.isEmpty());
    }
    static boolean checkNotEmpty2(List<String> list) {
        return CollectionUtils.isNotEmpty(list);
    }
}
