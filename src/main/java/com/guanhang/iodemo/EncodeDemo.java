package com.guanhang.iodemo;

import java.io.UnsupportedEncodingException;

/**
 * 查看不同编码的区别
 */
public class EncodeDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "我爱你ABC";
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            //结果：e6 88 91 e7 88 b1 e4 bd a0 41 42 43
            //项目是UTF-8， 3个字节一个汉字
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();
        byte[] gbk = s.getBytes("gbk");
        for (byte b : gbk) {
            //结果：ce d2 b0 ae c4 e3 41 42 43
            //项目是GBK， 2个字节一个汉字
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();
        //java是双字节编码：utf-16be,中英文都是两个字节,因此默认的字符串是该编码的
        //当字节序列是某种编码时，转换成字符串也需要相应的编码格式

        String str = new String(bytes, "utf-8");
        System.out.println(str);

        //文本文件就是字节序列，可以是任意编码
    }
}
