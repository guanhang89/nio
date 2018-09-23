package com.guanhang.nettyguide.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsynTimeServerHandler implements Runnable {

    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel channel;

    public AsynTimeServerHandler(int port) {
        this.port = port;
        try {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        //通过回调接口完成后续处理，传入的attachment即使本类
        channel.accept(this, new AcceptCompletionHander());
    }

    public class AcceptCompletionHander implements CompletionHandler<AsynchronousSocketChannel,AsynTimeServerHandler> {

        @Override
        public void completed(AsynchronousSocketChannel result, AsynTimeServerHandler attachment) {
            //每当接收一个连接成功后，再异步接收新的客户端连接
            attachment.channel.accept(attachment, this);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //又交由读的回调类处理
            result.read(buffer, buffer, new ReadCompletionHandler(result));
        }

        @Override
        public void failed(Throwable exc, AsynTimeServerHandler attachment) {
            exc.printStackTrace();
            //失败后结束程序
            attachment.latch.countDown();
        }
    }

}
