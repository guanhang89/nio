package com.guanhang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelMapDemo {

    public static void main(String[] args) {
        int BUFFER_SIZE = 1024;
        String filename = "test.db";
        long fileLength = new File(filename).length();
        int bufferCount = 1 + (int) (fileLength / BUFFER_SIZE);
        MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCount];
        long remaining = fileLength;
        for (int i = 0; i < bufferCount; i++) {
            RandomAccessFile file;
            try {
                file = new RandomAccessFile(filename, "r");
                buffers[i] = file.getChannel().map(FileChannel.MapMode.READ_ONLY, i * BUFFER_SIZE, Math.min(remaining, BUFFER_SIZE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            remaining -= BUFFER_SIZE;
        }
    }
}
