package com.kevin.base.classloader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author kevin.yang
 */
public class MyClassLoader {

    public static void main(String[] args) throws Exception {
        String srcPath = args[0];
        String destPath = args[1];
        FileInputStream fis = new FileInputStream(srcPath);
        FileOutputStream fos = new FileOutputStream(destPath);
        cypher(fis, fos);
        fis.close();
        fos.close();
    }

    public static void cypher(InputStream istream, OutputStream ostream) throws Exception {
        int b = 0;
        while ((b = istream.read()) != -1) {
            ostream.write(b ^ 0xff);
        }
    }
}
