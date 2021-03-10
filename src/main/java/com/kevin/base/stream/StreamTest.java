package com.kevin.base.stream;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kevin.base.BaseApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.util.Pair.toMap;

/**
 * @ClassName StreamTest
 * @Description stream
 * @Author kevin.yang
 * @Date 2021-03-08 21:39
 */
public class StreamTest extends BaseApp {

    public static void main(String[] args) {
        // 创建流
        println("createStream", true);
        createStream();

        println("filter", true);
        filter();

        println("map", true);
        map();

        println("limit", true);
        limit();

        println("skip", true);
        skip();

        println("sorted", true);
        sorted();

        println("distinct", true);
        distinct();

        println("count", true);
        count();

        println("collect", true);
        collect();

        println("test", true);
        test();

        println("flatMap", true);
        test2();
    }

    /**
     * 创建流:
     * 1、通过已有的集合来创建流
     * 2、通过stream创建流
     */
    private static void createStream() {
        // 1、通过已有的集合来创建流
        List<String> lists = Arrays.asList("Hello", "", "world", "!");
        Stream<String> stream = lists.stream();

        stream.forEach(System.out::println);

        // 2、通过stream创建流
        Stream<String> stream2 = Stream.of("AAA", "BBB", "CCC");
        stream2.forEach(System.out::println);
    }

    /**
     * filter 方法用于通过设置的条件过滤出元素
     */
    private static void filter() {
        List<String> lists = Arrays.asList("Hello", "", "world", "!");

        // 使用 filter 方法过滤掉空字符串
        lists.stream().filter(str -> !str.isEmpty()).forEach(System.out::println);
    }

    /**
     * map 方法用于映射每个元素到对应的结果
     */
    private static void map() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7);
        // 使用 map 输出了元素对应的平方数
        numbers.stream().map(i -> i * i).forEach(System.out::println);
    }

    /**
     * limit 返回 Stream 的前面 n 个元素
     */
    private static void limit() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7);
        numbers.stream().limit(4).forEach(System.out::println);
    }

    /**
     * skip 则是扔掉前 n 个元素
     */
    private static void skip() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7);
        numbers.stream().skip(4).forEach(System.out::println);
    }

    /**
     * sorted 方法用于对流进行排序
     */
    private static void sorted() {
        List<Integer> numbers = Arrays.asList(2, 3, 1, 2, 4, 5, 3, 6, 7);
        numbers.stream().sorted().forEach(System.out::println);
    }

    /**
     * distinct主要用来去重
     */
    private static void distinct() {
        List<Integer> numbers = Arrays.asList(2, 3, 1, 2, 4, 5, 3, 6, 7);
        numbers.stream().distinct().forEach(System.out::println);
    }

    /**
     * count用来统计流中的元素个数
     */
    private static void count() {
        List<Integer> numbers = Arrays.asList(2, 3, 1, 2, 4, 5, 3, 6, 7);
        System.out.println(numbers.stream().count());
    }

    /**
     * collect就是一个归约操作
     */
    private static void collect() {
        List<Integer> numbers = Arrays.asList(2, 3, 1, 2, 4, 5, 3, 6, 7);
        Set<Integer> sets = numbers.stream().collect(Collectors.toSet());
        System.out.println(sets);
    }

    private static void test() {
        List<User> list = new ArrayList<>();
        User user3 = new User(3, "abc");
        list.add(user3);
        User user2 = new User(2, "ab");
        list.add(user2);
        User user5 = new User(3, "abcde");
        list.add(user5);
        User user4 = new User(3, "abcd");
        list.add(user4);
        User user1 = new User(1, "a");
        list.add(user1);

        Map<Integer, User> result = list.stream().collect(Collectors.toMap(User::getId, User->User, (a, b) -> b));
        Map<Integer, User> result2 = list.stream().collect(Collectors.toMap(User::getId, Function.identity(), (a, b) -> b));
        list.forEach(System.out::println);
        System.out.println(result.toString());
        System.out.println(result2.toString());
    }


    private static void test2() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(3, 4);

//        List<int[]> result = list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i,j})).collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(result));

        List<List<int[]>> result2 = new ArrayList<>();
        for (Integer j : list1) {
            List<int[]> l1 = new ArrayList<>();
            for (Integer i : list2) {
                l1.add(new int[]{j, i});
            }

            result2.add(l1);
        }
        System.out.println(JSON.toJSONString(result2));
    }

}

class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
