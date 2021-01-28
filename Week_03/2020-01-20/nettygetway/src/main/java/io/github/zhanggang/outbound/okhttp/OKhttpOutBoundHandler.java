package io.github.zhanggang.outbound.okhttp;

import io.github.zhanggang.filter.HeaderHttpResponseFilter;
import io.github.zhanggang.filter.HttpRequestFilter;
import io.github.zhanggang.filter.HttpResponseFilter;
import io.github.zhanggang.outbound.httclient4.NamedThreadFactory;
import io.github.zhanggang.router.HttpEndpointRouter;
import io.github.zhanggang.router.RandomHttpEndpointRouter;
import io.github.zhanggang.router.WeightHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OKhttpOutBoundHandler {

    private List<String> backendUrls;
    private OkHttpClient client;
    private ExecutorService proxyService;

    HttpResponseFilter responseFilter = new HeaderHttpResponseFilter();
    //HttpEndpointRouter router = new RandomHttpEndpointRouter();
    HttpEndpointRouter router = new WeightHttpEndpointRouter();

    public OKhttpOutBoundHandler(List<String> backendUrls) {
        this.backendUrls = backendUrls.stream().map(this::formatUrl).collect(Collectors.toList());
        this.client = new OkHttpClient();

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

    }

    private String formatUrl(String backend) {
        return backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend;
    }

    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        proxyService.submit(() -> threadHandle(fullHttpRequest, ctx, filter));
//        threadHandle(fullHttpRequest, ctx, filter);
    }

    private void threadHandle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        FullHttpResponse response = null;
        try {
            //1.路由
            String backendUrl = router.route(this.backendUrls);
            final String url = backendUrl + fullHttpRequest.uri();

            //2.request filter
            filter.filter(fullHttpRequest, ctx);

            //3.请求
            String responseString = doGet(fullHttpRequest, url);

            //4.response filter
            byte[] body = responseString.getBytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", body.length);

            responseFilter.filter(response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }


    private String doGet(final FullHttpRequest inbound, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("hello", inbound.headers().get("hello"))
                .addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
