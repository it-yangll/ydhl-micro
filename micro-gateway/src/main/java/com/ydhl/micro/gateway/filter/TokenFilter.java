package com.ydhl.micro.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.gateway.consts.RedisKeyConst;
import com.ydhl.micro.gateway.properties.ConfigProperties;
import com.ydhl.micro.gateway.security.JwtBody;
import com.ydhl.micro.gateway.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TokenFilter
 * @Description 令牌过滤器
 * @Author yangll
 * @Date 2019-9-10 11:37:21
 * @Version 1.0
 **/
@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    private final String JWT_NAME = "Authorization";
    private static final String JWT_PAYLOAD_AES_KEY = "hFjjn2f6TyiCymLn";

    @Value("${token_exp_time}")
    private long expTime;

    @Autowired
    private ConfigProperties configProperties;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        if (isSkipUrl(request.getURI().toString())) {
            return chain.filter(exchange);
        }

        log.debug("【{}】请求认证", request.getURI());

        String token = request.getHeaders().getFirst(JWT_NAME);

        if (StringUtils.isBlank(token)) {
            token = request.getQueryParams().getFirst(JWT_NAME);
            log.debug("【{}】header不存在token，从url取【{}】", request.getURI(), token);
        }

        if (StringUtils.isBlank(token)) {
            HttpCookie cookie = request.getCookies().getFirst(JWT_NAME);
            if (cookie != null) {
                token = cookie.getValue();
            }
            log.debug("【{}】url不存在token，从cookis取【{}】", request.getURI(), token);
        }

        if (StringUtils.isBlank(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            JSONObject result = new JSONObject();
            result.put("success", false);
            result.put("code", "G004");
            result.put("message", "请登录");

            log.debug("【{}】不存在token,中止请求【{}】", request.getURI(), result);

            exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] bytes = result.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        } else {
            try {
                JwtBody body = JwtUtil.parseToJwtBody(token);

                String loginType = body.getLoginType().name();
                String userType = body.getUserType().name();

                //判断登录方式
                switch (loginType) {
                    case "MALL":
                        if (!request.getURI().toString().contains("/api/mall/")) {
                            throw new Exception("登录方式错误");
                        }
                        break;

                    case "ADMIN":
                        if (!request.getURI().toString().contains("/api/admin/")) {
                            throw new Exception("登录方式错误");
                        }
                        break;

                    case "LITEAPP":
                        if (!request.getURI().toString().contains("/api/liteapp/")) {
                            throw new Exception("登录方式错误");
                        }
                        break;

                    default:
                        throw new Exception("登录方式未知");
                }

                //从redis 取token 看是否失效
                String value = stringRedisTemplate.opsForValue().get(RedisKeyConst.getUserTokenKey(body.getIdentity(), body.getLoginType(), body.getUserType()));
                if (StringUtils.isBlank(value)) {
                    throw new Exception("令牌失效");
                } /*else if (body.getUserType() != UserType.ADMIN && !StringUtils.equals(value, token)) {
                    throw new Exception("已在其他地方登陆");
                }*/

                stringRedisTemplate.expire(RedisKeyConst.getUserTokenKey(body.getIdentity(), body.getLoginType(), body.getUserType()), expTime, TimeUnit.SECONDS);
            } catch (Exception e) {
                log.error("令牌校验异常", e);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                JSONObject result = new JSONObject();
                result.put("success", false);
                result.put("code", "G601");
                result.put("message", "令牌无效");

                log.debug("【{}】认证异常,中止请求【{}】", request.getURI(), result);

                exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                byte[] bytes = result.toJSONString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }
        }

        log.debug("【{}】认证通过", request.getURI());
        //向headers中放文件，记得build
        ServerHttpRequest host = exchange.getRequest().mutate().header(JWT_NAME, token).build();
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    private boolean isSkipUrl(String url) {
        for (String skipUrl : configProperties.getList()) {
            if (url.contains(skipUrl)) {
                log.debug("当前访问地址{}包含白名单{}，不进行token认证拦截！", url, skipUrl);
                return true;
            }
        }
        return false;
    }

}

