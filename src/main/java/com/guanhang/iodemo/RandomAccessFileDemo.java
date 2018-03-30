package com.guanhang.iodemo;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFileDemo {

    public static void main(String[] args) {
        try {
            //打开方式 rw读写 r只读
            File dir = new File("filedeomo");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "random.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            System.out.println(file.getAbsolutePath());


            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            System.out.println(randomAccessFile.getFilePointer());
            //write每次只写1个字节，即后8位
            randomAccessFile.write('A');
            System.out.println(randomAccessFile.getFilePointer());

            int i = 0x7ffffff;
            //写高八位
            randomAccessFile.write(i >> 24);
            randomAccessFile.write(i >> 16);
            randomAccessFile.write(i >> 8);
            randomAccessFile.write(i);
            System.out.println(randomAccessFile.getFilePointer());

            String s = "我爱你";
            byte[] bytes = s.getBytes("utf-8");
            randomAccessFile.write(bytes);
            System.out.println(randomAccessFile.length());
            randomAccessFile.seek(0);

            byte[] buf = new byte[(int) randomAccessFile.length()];
            randomAccessFile.read(buf);
            System.out.println(Arrays.toString(buf));

            randomAccessFile.close();        } catch (Exception e) {
            System.out.println("fail");
        }

    }



}
