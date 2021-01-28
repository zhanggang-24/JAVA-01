package io.github.zhanggang.inbound;

import io.github.zhanggang.filter.HeaderHttpRequestFilter;
import io.github.zhanggang.filter.HttpRequestFilter;
import io.github.zhanggang.outbound.httclient4.HttpOutboundHandler;
import io.github.zhanggang.outbound.okhttp.OKhttpOutBoundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

//业务处理类
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final List<String> proxyServer;

    private OKhttpOutBoundHandler oKhttpOutBoundHandler;
    //    private HttpOutboundHandler httpOutboundHandler;
    private HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.oKhttpOutBoundHandler = new OKhttpOutBoundHandler(this.proxyServer);
//        this.httpOutboundHandler = new HttpOutboundHandler(this.proxyServer);
    }

    //读取数据事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            oKhttpOutBoundHandler.handle(fullHttpRequest, ctx, requestFilter);
//            httpOutboundHandler.handle(fullHttpRequest, ctx, requestFilter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }


    }

    //数据读取完毕事件
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    //异常发生事件
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
