package com.ydhl.micro.gateway.filter;

import cn.hutool.http.useragent.UserAgentParser;
import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.gateway.properties.ConfigProperties;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @ClassName LogFilter
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:37:35
 * @Version 1.0
 **/
@Component
@Slf4j
public class LogFilter implements GlobalFilter, Ordered {

    @Autowired
    private ConfigProperties configProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();

        if (isStreamUrl(request.getURI().toString())) {
            return chain.filter(exchange);
        }

        //打印请求日志
        log.info("入参：method=【{}】,url=【{}】,queryParams=【{}】,ip=【{}】,cookies=【{}】,headers=【{}】,User-Agent=【{}】",
                request.getMethodValue(), request.getURI(), request.getQueryParams(), getIpAddr(request), request.getCookies(), request.getHeaders(), JSONObject.toJSONString(UserAgentParser.parse(request.getHeaders().getFirst("User-Agent"))));

        ServerHttpRequestDecorator requestDecorator = new ServerHttpRequestDecorator(request) {

            @Override
            public Flux<DataBuffer> getBody() {
                Flux<DataBuffer> body = super.getBody();

                return body.map(dataBuffer -> {
                    if (request.getHeaders().getFirst("Content-Type").toLowerCase().startsWith("multipart/form-data")) {
                        log.debug("入参（文件上传）：url=【{}】不打印requestBody", request.getURI());
                        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(dataBuffer.readableByteCount());
                        buffer.write(dataBuffer);
                        return buffer;
                    } else {
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        //这个就是request body的json格式数据
                        String bodyJson = new String(content, Charset.forName("UTF-8"));
                        log.info("入参：url=【{}】,requestBody=【{}】", request.getURI(), bodyJson);
                        //转成字节
                        byte[] bytes = bodyJson.getBytes();
                        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
                        buffer.write(bytes);
                        return buffer;
                    }
                });
            }
        };

        //重写原始响应
        BodyHandlerServerHttpResponseDecorator responseDecorator = new BodyHandlerServerHttpResponseDecorator(
                initBodyHandler(exchange, startTime), exchange.getResponse());

        return chain.filter(exchange.mutate().request(requestDecorator).response(responseDecorator).build());
    }

    @Override
    public int getOrder() {
        return -1000;
    }

    /**
     * 响应body处理，添加响应的打印
     *
     * @param exchange
     * @param startTime
     * @return
     */
    protected BodyHandlerFunction initBodyHandler(ServerWebExchange exchange, long startTime) {
        return (resp, body) -> {
            //解决shiro拦截导致跨域响应header参数值为多个，这里在最终响应客户端的地方 覆盖之前的跨域header
            resp.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            resp.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
            resp.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
            resp.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            resp.getHeaders().set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            resp.getHeaders().set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "18000L");


            String mediaType = exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, mediaType);
            DefaultClientResponseAdapter clientResponseAdapter = new DefaultClientResponseAdapter(body, httpHeaders);
            if (isStreamUrl(exchange.getRequest().getURI().toString()) || (StringUtils.isNotBlank(mediaType) && mediaType.toLowerCase().contains("application/octet-stream"))) {
                //打印返回响应日志
                log.info("出参（文件下载）:处理耗时=【{}】, HTTP状态码=【{}】,cookies=【{}】,headers=【{}】",
                        System.currentTimeMillis() - startTime, resp.getStatusCode(), resp.getCookies(), resp.getHeaders());

                Mono<byte[]> bodyMono = clientResponseAdapter.bodyToMono(byte[].class);
                return bodyMono.flatMap((respBody) -> {
                    return resp.writeWith(Flux.just(respBody).map(bx -> resp.bufferFactory().wrap(bx)));
                }).then();
            } else {
                Mono<String> bodyMono = clientResponseAdapter.bodyToMono(String.class);
                return bodyMono.flatMap((respBody) -> {
                    //打印返回响应日志
                    log.info("出参：处理耗时=【{}】, HTTP状态码=【{}】,cookies=【{}】,headers=【{}】,body=【{}】",
                            System.currentTimeMillis() - startTime, resp.getStatusCode(), resp.getCookies(), resp.getHeaders(), respBody);
                    return resp.writeWith(Flux.just(respBody).map(bx -> resp.bufferFactory().wrap(bx.getBytes())));
                }).then();
            }
        };
    }

    private boolean isStreamUrl(String url) {
        for (String skipUrl : configProperties.getNonLog()) {
            if (url.contains(skipUrl)) {
                log.debug("当前访问地址【{}】包含白名单【{}】，不打印responseBody内容", url, skipUrl);
                return true;
            }
        }
        return false;
    }

    public static String getIpAddr(ServerHttpRequest request) {
        String ip = null;
        try {
            ip = request.getHeaders().getFirst("x-forwarded-for");
            log.debug("x-forwarded-for ip: {}", ip);
            if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if (ip.indexOf(",") != -1) {
                    ip = ip.split(",")[0];
                }
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("Proxy-Client-IP");
                log.debug("Proxy-Client-IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
                log.debug("WL-Proxy-Client-IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("HTTP_CLIENT_IP");
                log.debug("HTTP_CLIENT_IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("HTTP_X_FORWARDED_FOR");
                log.debug("HTTP_X_FORWARDED_FOR ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("X-Real-IP");
                log.debug("X-Real-IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddress().toString();
                log.debug("getRemoteAddr ip: {}", ip);
            }
            log.debug("获取客户端ip: {}", ip);
        } catch (Exception e) {
            log.error("获取客户端IP异常！", e);
        }
        return ip;
    }
}
