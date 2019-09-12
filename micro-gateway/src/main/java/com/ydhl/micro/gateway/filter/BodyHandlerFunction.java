package com.ydhl.micro.gateway.filter;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

/**
 * @ClassName BodyHandlerFunction
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:36:57
 * @Version 1.0
 **/
public interface BodyHandlerFunction
        extends BiFunction<ServerHttpResponse, Publisher<? extends DataBuffer>, Mono<Void>> {
}

