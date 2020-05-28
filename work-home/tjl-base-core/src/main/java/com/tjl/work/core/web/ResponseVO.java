package com.tjl.work.core.web;

import java.io.Serializable;

/**
 * 功能描述
 * @author tjl
 * @Type ResponseVO
 * @date 2020/5/25 15:55
 * @Version 1.0
 */
public class ResponseVO<T> implements Serializable {
    private String message = "ok";
    private T data;
    private int code = 200;

    public ResponseVO(int code, String message, T data){
        this.code = code;
        this.data =data;
        this.message = message;
    }
    public ResponseVO(){

    }
    public ResponseVO(T data){
        this.data =data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }
}
