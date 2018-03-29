package com.guanhang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ByteBufferDemo {


    public static void readFile(String fileName) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(20);
            int size = fileChannel.read(byteBuffer);
            while (size > 0) {
                //读写互换
                byteBuffer.flip();
                Charset charset = Charset.forName("UTF-8");
                System.out.println(charset.newDecoder().decode(byteBuffer).toString());

                byteBuffer.clear();

                size = fileChannel.read(byteBuffer);
            }
            fileChannel.close();
            randomAccessFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile("1.txt");
    }
}
