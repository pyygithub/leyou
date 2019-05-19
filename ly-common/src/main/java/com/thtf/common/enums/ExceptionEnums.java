package com.thtf.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义异常枚举
 * @author pyy
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    ;
    private int code;
    private String msg;
}
