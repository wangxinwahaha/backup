package com.unisinsight.framework.uuv.base;

/**
 * 响应结果生成工具
 *
 * @author  tanjiquan [KF.tanjiquan@h3c.com]
 * @date    2018/9/6 12:42
 * @since   1.0
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setSuccess(true);
    }
//
//    public static <T> Result<T> genSuccessResult(T data) {
//        return new Result()
//                .setCode(ResultCode.SUCCESS)
//                .setMessage(DEFAULT_SUCCESS_MESSAGE)
//                .setData(data)
//                .setSuccess(true);
//    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setSuccess(false);
    }

    public static Result genFailResult(Integer code,String message) {
        return new Result()
                .setCode(ResultCode.getByCode(code))
                .setMessage(message)
                .setSuccess(false);
    }


}
