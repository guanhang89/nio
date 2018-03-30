package com.guanhang.iodemo;

import java.io.File;

public class FileDemo {

    public static void main(String[] args) {
        //相对路径是基于当前项目目录的
        //File.seperator
        File file = new File("1.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        //多级目录创建用mkdirs
    }
}
