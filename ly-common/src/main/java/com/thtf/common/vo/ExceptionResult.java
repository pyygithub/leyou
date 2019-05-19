package com.thtf.common.vo;

import com.thtf.common.enums.ExceptionEnums;
import lombok.Data;

/**
 * 自定义异常响应结果数据
 * @author pyy
 */
@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
