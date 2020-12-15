package com.kevin.base.datatype;

import com.kevin.base.BaseApp;

/**
 * @ClassName SwitchTest
 * @Description switch
 * @Author kevin.yang
 * @Date 2020-12-15 18:27
 */
public class SwitchTest extends BaseApp {

    public static void main(String[] args) {
        /**
         * switch中只能使用整型。char(ascii码)、string(hashCode())也是转成整型
         */
        println("switchInt", true);
        switchInt();

        println("switchChar", true);
        switchChar();

        println("switchString", true);
        switchString();
    }

    /**
     * int
     * 原理：switch对int的判断是直接比较整数的值。
     */
    private static void switchInt(){
        int a = 5;
        switch (a){
            case 1:
                println(1 + "", false);
                break;
            case 5:
                println(5 + "", false);
                break;
            default:
                break;
        }
    }

    /**
     * char
     * 原理：switch对char类型进行比较的时候，实际上比较的是ascii码
     */
    private static void switchChar(){
        char a = 'b';
        switch (a){
            case 'a':
                println("a", false);
                break;
            case 'b':
                println("b", false);
                break;
            default:
                break;
        }
    }

    /**
     * string
     * 原理：switch对string类型进行比较的时候，实际上是通过equals()和hashCode()方法来实现的。
     */
    private static void switchString(){
        String a = "hello";
        switch (a){
            case "hello":
                println("hello", false);
                break;
            case "world":
                println("world", false);
                break;
            default:
                break;
        }
    }
}
