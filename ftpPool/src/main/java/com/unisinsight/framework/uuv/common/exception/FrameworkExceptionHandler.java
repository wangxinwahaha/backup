/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * description 统一异常处理
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@ControllerAdvice
public class FrameworkExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, FrameworkException.class})
    public ResponseEntity<Object> handleOtherExceptions(final Exception ex, final WebRequest req) {
        ErrorResult result = new ErrorResult();

        if (ex instanceof FrameworkException){
            result.setErrorCode(((FrameworkException) ex).getErrorCode());
            result.setMessage(ex.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException){
            List<FieldError> errors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            List<String> messages = new ArrayList<>();
            for (FieldError error : errors){
                String line = com.unisinsight.framework.uuv.common.utils.StringUtils.underscoreName(error.getField())
                        + ":" + error.getDefaultMessage();

                messages.add(line);
            }

            String message = StringUtils.join(messages.toArray(), ";");
            message = message +  "参数异常";
            result.setErrorCode(ErrorCode.BASE_PARAM_VALID_ERROR.getErrorCode());
            result.setMessage(message);
        }

        ex.printStackTrace();

        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    class ErrorResult {
        private String errorCode;
        private String message;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}