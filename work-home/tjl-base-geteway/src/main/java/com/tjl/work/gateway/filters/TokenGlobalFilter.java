package com.tjl.work.gateway.filters;

import com.alibaba.fastjson.JSONObject;
import com.tjl.work.core.models.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 功能描述 token 统一检验 角色权限筛查
 * @author tjl
 * @Type TokenGlobalFilter
 * @date 2020/5/20 15:51
 * @Version 1.0
 */
public class TokenGlobalFilter implements GlobalFilter, Ordered {

    private RedisTemplate redisTemplate;

    public TokenGlobalFilter(){

    }

    public TokenGlobalFilter(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    private Logger logger = LoggerFactory.getLogger(TokenGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        String uri = exchange.getRequest().getURI().getPath();
        if(uri.contains("/user/login")){
            return chain.filter(exchange);
        }
        List<String> tokens = httpHeaders.get("x-auth-token");
        String token = null;
        if(tokens != null && tokens.size() > 0){
            token = tokens.get(0);
        }
        logger.info("=======作用于全局过滤器 token is {}...", token);

        if(token == null || redisTemplate.opsForValue().get(token) == null){
            ServerHttpResponse response = exchange.getResponse();
            JSONObject message = new JSONObject();
            message.put("code", -1);
            message.put("data", "");
            message.put("message", "登录失效");
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        UserVO userVO = (UserVO)redisTemplate.opsForValue().get(token);
        logger.info(userVO.toString());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
