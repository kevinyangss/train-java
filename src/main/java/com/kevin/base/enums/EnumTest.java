package com.kevin.base.enums;

import com.kevin.base.BaseApp;

/**
 * @author kevin.yang
 */
public class EnumTest extends BaseApp {

    public static void main(String[] args) {
        System.out.println(AbstractEnumTest.RED.newxtLamp());

        println("enum test", true);
        println(String.format("Enum getCodeByStatus: %d", StatusEnum.getCodeByStatus(StatusEnum.FINISH.status)), false);
    }

}
