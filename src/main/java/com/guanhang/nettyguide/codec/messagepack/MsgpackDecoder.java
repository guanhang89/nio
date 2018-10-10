package com.guanhang.nettyguide.codec.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] array = new byte[msg.readableBytes()];
        msg.getBytes(msg.readerIndex(), array, 0, array.length);
        out.add(messagePack.read(array));
    }
}
