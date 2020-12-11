package com.kevin.base.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kevin.yang
 */
public class UrlOpenConnection {
    public static void main(String[] args) {
        System.out.println("doGet()>>>>>" + doGet());
        System.out.println("doPost()>>>>>" + doPost());
    }

    private static String doGet() {
        String result = "";
        BufferedReader in = null;
        try {
            String urlString = "http://www.baidu.com";
            URL url = new URL(urlString);
            //打开和url之间的连接
            URLConnection connection = url.openConnection();
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立实际的连接
            connection.connect();

            //获取所有的响应头字段
            Map<String, List<String>> headMap = connection.getHeaderFields();
            for (String key : headMap.keySet()) {
                System.out.println(key + "--->" + headMap.get(key));
            }

            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {// 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return result;
    }

    private static String doPost() {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String urlString = "http://www.baidu.com";
            URL url = new URL(urlString);
            // 打开和URL之间的连接
            URLConnection connection = url.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //post请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            //out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {//使用finally块来关闭输出流、输入流
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
