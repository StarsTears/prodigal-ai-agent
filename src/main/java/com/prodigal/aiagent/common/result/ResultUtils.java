package com.prodigal.aiagent.common.result;


import com.prodigal.aiagent.exception.ErrorCode;

/**
 * @project:  prodigal-ai-agent
 * @author: Lang
 * @description: 响应工具类
 **/
public class ResultUtils {
    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<T>(0, true, "success", data);
    }
    public static  BaseResult<?> error(ErrorCode errorCode) {
        return new BaseResult<>(errorCode);
    }
    public static <T> BaseResult<T> error(int code,String msg) {
        return new BaseResult<>(code, false, msg, null);
    }
    public static <T> BaseResult<T> error(ErrorCode errorCode,String msg) {
        return new BaseResult<>(errorCode.getCode(), false, msg, null);
    }
}
