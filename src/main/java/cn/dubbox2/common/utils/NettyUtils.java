package cn.dubbox2.common.utils;

import io.netty.channel.ChannelFuture;
import io.netty.util.Signal;
import io.netty.util.concurrent.DefaultPromise;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * @author buchengyin
 * @Date 2018/12/24 10:16
 **/
public class NettyUtils {

    /**
     * 关闭netty服务端
     * @param closeFuture
     */
    public static void closeChannel(ChannelFuture closeFuture){
        try {
            synchronized (closeFuture) {
                closeFuture.channel().unsafe().closeForcibly();
                System.out.println(">>>>>>>>>>>>>>>>>>invoke await method");
                Field field = DefaultPromise.class.getDeclaredField("result");
                field.setAccessible(true);
                field.set(closeFuture, Signal.valueOf("SUCCESS"+UUID.randomUUID()));
                closeFuture.notifyAll();
            }
            closeFuture.channel().unsafe().closeForcibly();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
