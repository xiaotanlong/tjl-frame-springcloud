package com.tjl.work.core.web;

/**
 * 功能描述
 * @author tjl
 * @Type RetCode
 * @date 2020/5/25 16:03
 * @Version 1.0
 */
public enum RetCode {
    // 成功
    SUCCESS(200),

    // 失败
    FAIL(202),

    // 未认证（签名错误）
    UNAUTHORIZED(401),

    // 接口不存在
    NOT_FOUND(404),

    //服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    RetCode(int code) {
        this.code = code;
    }
}
