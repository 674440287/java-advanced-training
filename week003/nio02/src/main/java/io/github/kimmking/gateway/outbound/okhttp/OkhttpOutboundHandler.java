package io.github.kimmking.gateway.outbound.okhttp;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.outbound.OutBoundHandler;
import io.github.kimmking.gateway.outbound.httpclient4.HttpClientRequest;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class OkhttpOutboundHandler implements OutBoundHandler {

    private final OkHttpClientRequest okHttpClientRequest;

    private ExecutorService proxyService;
    private List<String> backendUrls;

    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public OkhttpOutboundHandler(List<String> backends) {

        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;

        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize));
        okHttpClientRequest = new OkHttpClientRequest(cores);
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, List<HttpRequestFilter> filters) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        for (HttpRequestFilter filter:filters){
            filter.filter(fullRequest, ctx);
        }
        proxyService.submit(()-> okHttpClientRequest.fetchGet(fullRequest, ctx, url));
    }

}