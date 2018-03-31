package com.guanhang.iodemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo {

    //字节流 InputStream OutputStream
    //输入流 in.read()读取一个字节
    // in.read(byte[],start,length)
    //输出流 out.write(int b) out.write(byte[])
    public static void main(String[] args) throws IOException {
        File file = new File("1.txt");
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
        }
        FileInputStream in = new FileInputStream("1.txt");
        int b ;
        while ((b = in.read()) != -1) {
            System.out.print(Integer.toHexString(b));
        }
        //如果没有该文件会创建，如果存在，会删除后再创建
//        FileOutputStream out = new FileOutputStream("2.txt");

    }
}
