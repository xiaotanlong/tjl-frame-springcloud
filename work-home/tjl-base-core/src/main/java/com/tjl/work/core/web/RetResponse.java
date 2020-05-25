package com.tjl.work.core.web;

/**
 * 功能描述
 * @author tjl
 * @Type RetResponse
 * @date 2020/5/25 16:04
 * @Version 1.0
 */
public class RetResponse {
    //自定义message 失败信息
    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<T>(data);
    }

    //自定义code,msg 返回数据
    public static <T> ResponseVO<T> makeRsp(int code, String msg) {
        return new ResponseVO<T>(code,msg,null);
    }
    //自定义code,msg,data 返回数据
    public static <T> ResponseVO<T> makeRsp(int code, String msg, T data) {
        return new ResponseVO<T>(code,msg,data);
    }
}
