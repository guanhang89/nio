package com.guanhang.iodemo;

import java.io.*;

public class FileUtil {

    public static void copy(String srcFileName, String dstFileName) throws IOException {
        File srcFile = new File(srcFileName);
        if (!srcFile.exists()) {
            srcFile.createNewFile();
        }
        FileInputStream fileInputStream = new FileInputStream(srcFile);
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        byte[] bytes = new byte[1024];
        int b;
        while ((b = fileInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes);
        }
        fileInputStream.close();
        fileOutputStream.close();

    }

    public static void cpoyWithBuffer(String src, String dst) throws IOException {
        File srcFile = new File(src);
        if (!srcFile.exists()) {
            srcFile.createNewFile();
        }
        BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(dst));
        int b;
        //比不带缓冲的要快,因为读的是内存
        while ((b = fileInputStream.read()) != -1) {
            fileOutputStream.write(b);
            fileOutputStream.flush();

        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        copy("1.txt", "2.txt");
    }
}
