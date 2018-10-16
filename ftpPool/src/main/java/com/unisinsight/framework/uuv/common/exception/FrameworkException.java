/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.exception;

/**
 * description 统一异常处理
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class FrameworkException extends RuntimeException {

    public FrameworkException(String message){
        super(message);
    }

    private String errorCode;

    public static FrameworkException of(ErrorCode errorCode){
        FrameworkException exception = new FrameworkException(errorCode.getMessage());
        exception.errorCode = errorCode.getErrorCode();

        return exception;
    }

    public static FrameworkException of(ErrorCode errorCode, String message){
        FrameworkException exception = new FrameworkException(message);
        exception.errorCode = errorCode.getErrorCode();

        return exception;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
