package com.thtf.common.exception;

import com.thtf.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * @author pyy
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommonException extends RuntimeException{

    private ExceptionEnums exceptionEnums;
}
