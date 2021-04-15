package com.kevin.base.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName StreamTransCharTest
 * @Description 字符流 与 字节流 互相转换
 * @Author kevin.yang
 * @Date 2021-04-14 10:52
 */
public class StreamTransCharTest {

    public static void main(String[] args) {
        try {
            // 字符流转成字节流
//            charTransStream();

            // 字节流转成字符流
            streamTransChar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符流转成字节流
     */
    private static void charTransStream() throws IOException {
        File f = new File("C:\\Users\\lester\\Desktop\\test.txt");

        // OutputStreamWriter 是字符流通向字节流的桥梁,创建了一个字符流通向字节流的对象
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
        osw.write("我是字符流转换成字节流输出的");
        osw.close();
    }

    /**
     * 字节流转成字符流
     */
    private static void streamTransChar() throws IOException {
        File f = new File("C:\\Users\\lester\\Desktop\\test.txt");

        InputStreamReader inr = new InputStreamReader(new FileInputStream(f), "UTF-8");

        char[] buf = new char[1024];
        int len = inr.read(buf);
        System.out.println(new String(buf,0,len));

        inr.close();
    }
}
