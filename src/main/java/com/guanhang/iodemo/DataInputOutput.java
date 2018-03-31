package com.guanhang.iodemo;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataInputOutput {

    //对流的功能的扩展，可以更加方便的读取int，long，字符等类型数据
    //使用了装饰着模式，底层还是操作普通的流
    public static void main(String[] args) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        dos.write(10);
        dos.writeInt(-10);
        dos.writeUTF("中文");
        dos.close();
    }
}
