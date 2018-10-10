package com.guanhang.nettyguide.codec.messagepack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = getUserInfos();
        for (UserInfo info : infos) {
            ctx.write(info);
        }
        ctx.flush();
        System.out.println("Client have sent msgs");
    }

    private UserInfo[] getUserInfos() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("ABCDEFG--->" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client received msg: " + msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Exception ");
        cause.printStackTrace();
        ctx.close();
    }

}
