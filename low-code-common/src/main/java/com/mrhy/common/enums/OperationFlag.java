package com.mrhy.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cooper
 */

@AllArgsConstructor
@Getter
public enum OperationFlag {
    /**
     * 成功
     */
    SUCCESS(0, "operate success"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(-1, "system error"),
    /**
     *业务错误
     */
    BUSINESS_ERROR(-2,"business error"),
    /**
     * 参数违法
     */
    ILLEGAL_ARGUMENT(-3, "illegal argument"),
    ;

    private final Integer returnCode;

    private final String description;
}
