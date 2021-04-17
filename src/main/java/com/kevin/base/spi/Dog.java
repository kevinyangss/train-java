package com.kevin.base.spi;

/**
 * @ClassName Dog
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 11:21
 */
public class Dog implements IShout{
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
