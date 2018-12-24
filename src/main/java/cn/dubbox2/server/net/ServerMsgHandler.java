package cn.dubbox2.server.net;

import cn.dubbox2.common.entity.RemoteStruct;
import cn.dubbox2.common.utils.ReflectUtils;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author buchengyin
 * @Date 2018/12/24 10:22
 **/
public class ServerMsgHandler extends SimpleChannelInboundHandler<String> {
    private Executor executor = Executors.newFixedThreadPool(30);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                handlerMsg(ctx,msg);
            }
        });
    }

    private void handlerMsg(ChannelHandlerContext ctx,String msg){
        RemoteStruct struct = JSON.parseObject(msg,RemoteStruct.class);
        Object value =  ReflectUtils.invokeMethod(struct.getClassFullName(),struct.getMethodFullName(),struct.getParameterFullTypes(),struct.getParameterValue());
        String result = JSON.toJSONString(value);
        ctx.writeAndFlush(result);
    }
}
