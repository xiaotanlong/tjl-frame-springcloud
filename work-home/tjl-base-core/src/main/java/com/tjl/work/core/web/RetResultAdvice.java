package com.tjl.work.core.web;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;

/**
 * 功能描述
 * @ ControllerAdvice Spring内置对其各个逻辑的织入方式进行了内置支持
 * @ ResponseBodyAdvice：在 spring 4.1 新加入的一个接口，
 *      在消息体被HttpMessageConverter写入之前允许Controller 中 @ResponseBody修饰的方法或ResponseEntity调整响应中的内容，
 *      比如进行相应的加密。
 * @author tjl
 * @Type RetResultAdvice
 * @date 2020/5/25 16:11
 * @Version 1.0
 */
@RestControllerAdvice
public class RetResultAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 功能描述:
     */
    public RetResultAdvice(){
        System.out.println("=========RetResultAdvice init ========");
    }

    /**这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行**/
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //如果是返回了RetResult类就直接返回不做处理
        if (o instanceof ResponseVO) {
            return o;
        }
        //如果返回的数据是string类型的时候做的处理
        if(o instanceof String) {
            return o;
        }

        // springBoot 自定义的异常类
        if(o instanceof LinkedHashMap) {
            LinkedHashMap map = (LinkedHashMap) o;
            StringBuffer message = new StringBuffer();
            message.append(map.getOrDefault("error", ""));
            message.append(" -> ");
            message.append(map.getOrDefault("message", ""));
            return RetResponse.makeRsp((int) map.getOrDefault("status", 500), message.toString(), null);
        }
        return RetResponse.success(o);
    }
}

