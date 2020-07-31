package com.stu.groupchat;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class NioGroupchatApplication {

    public static void main(String[] args) throws IOException {

    }

//    private String BinaryToChinese(String input){
//        StringBuilder sb= new StringBuilder();//建立string
//
//        int numOfBytes= input.length() / 8;
//        byte[] bytes = new byte[numOfBytes];
//
//        for(int i=0;i< numOfBytes ; ++i){
//            //对字符串转成对应二进制
//            bytes[i]= Converter.toBytes(input.substring(8*i,8),2);
//        }
//        //解码得到汉字
//        return System.Text.Encoding.Unicode.GetString(bytes);
//    }



}
