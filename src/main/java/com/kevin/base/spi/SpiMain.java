package com.kevin.base.spi;

import java.util.ServiceLoader;

/**
 * 如何定义SPI
 * 步骤1、定义一组接口 (假设是com.kevin.base.spi.IShout)，并写出接口的一个或多个实现，(假设是com.kevin.base.spi.Dog、com.kevin.base.spi.Cat)。
 * 步骤2、在 src/main/resources/ 下建立 /META-INF/services 目录， 新增一个以接口命名的文件 (com.kevin.base.spi.IShout)，
 *      内容是要应用的实现类（这里是com.kevin.base.spi.Dog、com.kevin.base.spi.Cat，每行一个类）。
 * 步骤3、使用 ServiceLoader 来加载配置文件中指定的实现。
 * @ClassName SpiMain
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-04-17 11:24
 */
public class SpiMain {

    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
    }
}
