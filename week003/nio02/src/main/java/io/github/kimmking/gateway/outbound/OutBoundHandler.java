package io.github.kimmking.gateway.outbound;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public interface OutBoundHandler {

    void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, List<HttpRequestFilter> filters);

}
