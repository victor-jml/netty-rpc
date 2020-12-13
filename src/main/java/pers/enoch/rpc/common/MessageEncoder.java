package pers.enoch.rpc.common;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author: zy
 * @Date: 2020/12/13 15:37
 * @Description: 消息编码器
 */
public class MessageEncode extends MessageToMessageEncoder<MessageOutPut> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageOutPut messageOutPut, List<Object> list) throws Exception {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer();
        writeStr(buf,messageOutPut.getRequestId());
        writeStr(buf,messageOutPut.getType());
        writeStr(buf, JSON.toJSONString(messageOutPut.getPayload()));
        list.add(buf);
    }

    private void writeStr(ByteBuf buf,String s){
        buf.writeInt(s.length());
        buf.writeBytes(s.getBytes(Charset.forName("utf8")));
    }
}
