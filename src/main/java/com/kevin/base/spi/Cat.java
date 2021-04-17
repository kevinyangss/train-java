package com.kevin.base.spi;

/**
 * @ClassName Cat
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 11:20
 */
public class Cat implements IShout{
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}
