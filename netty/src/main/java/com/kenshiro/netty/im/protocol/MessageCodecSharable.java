package com.kenshiro.netty.im.protocol;

import com.kenshiro.netty.im.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> list) throws Exception {
        ByteBuf out = ctx.alloc().buffer();
        //1. 4个字节的魔数
        out.writeBytes(new byte[]{1, 2, 3, 4});
        //2. 1个字节的版本
        out.writeByte(1);
        //3. 1个字节的序列化方式 JDK 0  JSON 1
        out.writeByte(0);
        //4. 1个字节的指令类型
        out.writeByte(msg.getMessageType());
        //5. 4个字节的请求序号
        out.writeInt(msg.getSequenceId());
        out.writeByte(0xff); //对齐填充

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos  = new ObjectOutputStream(baos);
        oos.writeObject(msg);
        byte[] bytes = baos.toByteArray();

        //6. 长度
        out.writeInt(bytes.length);
        //7. 内容
        out.writeBytes(bytes);
        list.add(out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serialType = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();
        in.readByte();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);
        if (serialType == 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                Message message = (Message) ois.readObject();
                out.add(message);
                log.debug("{}", message);
            }
        }
        log.debug("=============={},{},{},{},{},{}", magicNum, version, serialType, messageType, sequenceId, length);
    }
}
