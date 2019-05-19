package com.thtf.common.advice;

import com.thtf.common.enums.ExceptionEnums;
import com.thtf.common.exception.CommonException;
import com.thtf.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局通用异常处理器
 * @author pyy
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ExceptionResult> handleException(CommonException e) {
        return ResponseEntity.status(e.getExceptionEnums().getCode())
                .body(new ExceptionResult(e.getExceptionEnums()));
    }
}
