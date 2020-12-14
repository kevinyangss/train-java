package com.kevin.base.datatype;

import com.kevin.base.BaseApp;

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
}
