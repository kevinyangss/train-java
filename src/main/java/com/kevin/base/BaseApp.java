package com.kevin.base;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName BaseTest
 * @Description 测试基础类
 * @Author kevin.yang
 * @Date 2020-12-11 18:26
 */
public class BaseApp {

    /**
     * 打印
     * @param string
     */
    public static void println(String string, boolean isTip){
        if (isTip){
            System.out.println(String.format("<=========== %s =============>", string));
        } else {
            System.out.println(String.format("$$ %s $$", string));
        }
    }
}
