package io.github.zhanggang.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {
    void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}
