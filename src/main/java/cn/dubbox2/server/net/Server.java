package cn.dubbox2.server.net;

import cn.dubbox2.common.constant.SystemConstant;
import cn.dubbox2.common.utils.NettyUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author buchengyin
 * @Date 2018/12/24 10:07
 **/
public class Server {

    @Value("${dubbox2.server.port}}")
    private Integer port;
    private ChannelFuture closeFuture;
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public void start() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                     ch.pipeline().addLast("decode1",new LengthFieldBasedFrameDecoder(1024,0,4,0,4));
                     ch.pipeline().addLast("decode2",new StringDecoder());
                     ch.pipeline().addFirst("encode1",new StringEncoder());
                     ch.pipeline().addFirst("encode2",new LengthFieldPrepender(4));
                     ch.pipeline().addLast("myHandler", new ServerMsgHandler());
                }
            });

            ChannelFuture future = bootstrap.bind(port).sync();
            closeFuture = future.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        logger.info(SystemConstant.SERVER_PRE+" server start success");
                    }else{
                        logger.info(SystemConstant.SERVER_PRE+" server start fail");
                    }
                }
            }).channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            NettyUtils.closeChannel(closeFuture);
        }
    }
}
