package com.guanhang.iodemo;

import java.io.File;

public class FileDemo {

    public static void main(String[] args) {
        //相对路径是基于classpath下的
        //File.seperator
        File file = new File("1.txt");
        System.out.println(file.exists());

    }
}
