package com.kevin.base.datatype;

import com.kevin.base.BaseApp;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringTest
 * @Description 字符串操作
 * @Author yangshuaishuai
 * @Date 2020-12-14 16:26
 */
public class StringTest extends BaseApp {

    public static void main(String[] args) {
        // 字符串的不可变性
        println("new string", true);
        newString();

        // replace
        println("replace", true);
        replace();

        // replaceAll
        println("replaceAll", true);
        replaceAll();

        // replaceFirst
        println("replaceFirst", true);
        replaceFirst();

        // substring
        println("substring", true);
        substring();

        // 字符串拼接
        println("concat", true);
        concat();
        // 字符串拼接效率
        println("concatRate", true);
        concatRate();
    }

    /**
     * 字符串的不可变性
     */
    private static void newString(){
        String s = "abcd";
        String s1 = s;

        println("s == s1: " + (s == s1), false);
        println("s equals s1: " + (s.equals(s1)), false);

        s1 = s.concat("ef");
        println("s == s1: " + (s == s1), false);
        println("s equals s1: " + (s.equals(s1)), false);
    }

    /**
     * 替换
     */
    private static void replace(){
        String str = "abac";
        println(String.format("replace origin val：%s, final val: %s", str, str.replace("a", "\\a")), false);
    }

    /**
     * 替换符合正则的所有文字
     */
    private static void replaceAll(){
        String str = "正则表达式 Hello World,正则表达式 Hello World";
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher(str);

        // 替换所有符合正则的数据
        println(String.format("replaceAll origin val: %s, final val: %s", str, matcher.replaceAll("Java")), false);
    }

    /**
     * 替换第一个符合正则的数据
     */
    private static void replaceFirst(){
        String str = "正则表达式 Hello World,正则表达式 Hello World";
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher(str);

        // 替换所有符合正则的数据
        println(String.format("replaceFirst origin val: %s, final val: %s", str, matcher.replaceFirst("Java")), false);
    }

    /**
     * 字符串截取
     */
    private static void substring(){
        String str = "abcdef";
        println(String.format("replaceAll origin val: %s, final val: %s", str, str.substring(1, 3)), false);
    }

    /**
     * 字符串拼接
     */
    private static void concat(){
        // 方式一：concat，原理：创建一个字符数组，长度是已有字符串和待拼接字符串的长度之和，再把两个字符串的值复制到新的字符数组中，并使用这个字符数组创建一个新的String对象并返回。
        String str = "abc";
        str = str.concat(",test");
        println(String.format("concat val: %s", str), false);

        // 方式二：+，原理：将String转成了StringBuilder后，使用其append方法处理
        String str2 = "abc" + "," + "test";
        println(String.format("String + val: %s", str2), false);

        // 方式三: StringBuffer，线程安全，sychronized，原理：非final的数组
        StringBuffer strBuffer = new StringBuffer("abc");
        strBuffer.append(",").append("test");
        println(String.format("StringBuffer val: %s", strBuffer.toString()), false);

        // 方式四：StringBuilder
        StringBuilder strBuilder = new StringBuilder("abc");
        strBuilder.append(",").append("test");
        println(String.format("StringBuilder val: %s", strBuilder.toString()), false);

        // 方式五：apache.commons中提供的StringUtils类，原理：StringBuilder来实现的
        String str3 = "abc";
        String str4 = "test";
        String str5 = StringUtils.join(str3, ",", str4);
        println(String.format("StringUtils.join val: %s", str5), false);

        // StringUtils中提供的join方法，最主要的功能是：将数组或集合以某拼接符拼接到一起形成新的字符串
        String[] strList = {"abc", "test"};
        String str6 = StringUtils.join(strList, ",");
        println(String.format("StringUtils.join Array val: %s", str6), false);
    }

    /**
     * 字符串效率比较
     */
    private static void concatRate(){
        long t1 = System.currentTimeMillis();
        String str = "abc";
        for (int i = 0; i < 50000; i++) {
            str = str + "," + i;
        }
        long t2 = System.currentTimeMillis();
        println(String.format("+ cost: %s", (t2 - t1)), false);

        long t3 = System.currentTimeMillis();
        String str1 = "abc";
        for (int i = 0; i < 50000; i++) {
            str1 = str1.concat(",").concat(String.valueOf(i));
        }
        long t4 = System.currentTimeMillis();
        println(String.format("concat cost: %s", (t4 - t3)), false);

        long t5 = System.currentTimeMillis();
        StringBuffer strBuffer = new StringBuffer("abc");
        for (int i = 0; i < 50000; i++) {
            strBuffer.append(",").append(i);
        }
        long t6 = System.currentTimeMillis();
        println(String.format("StringBuffer cost: %s", (t6 - t5)), false);

        long t7 = System.currentTimeMillis();
        StringBuilder strBuilder = new StringBuilder("abc");
        for (int i = 0; i < 50000; i++) {
            strBuilder.append(",").append(i);
        }
        long t8 = System.currentTimeMillis();
        println(String.format("StringBuilder cost: %s", (t8 - t7)), false);

        long t9 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            StringUtils.join("abc", ",", i);
        }
        long t10 = System.currentTimeMillis();
        println(String.format("StringUtils.join cost: %s", (t10 - t9)), false);
    }
}
