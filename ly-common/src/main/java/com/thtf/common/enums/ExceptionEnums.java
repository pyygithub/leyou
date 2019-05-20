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
    PRICE_CANNOT_BE_NULL(400, "价格不能为空！"),
    BAD_REQUEST_PARAMETER(400, "请求参数错误"),
    CATEGORY_NOT_FOUND(404, "商品分类没查询到！"),
    BRAND_NOT_FOUND(404, "品牌列表没查询到！")
    ;
    private int code;
    private String msg;
}
