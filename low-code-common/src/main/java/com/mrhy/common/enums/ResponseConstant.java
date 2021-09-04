package com.mrhy.common.enums;

public enum ResponseConstant {
    /**
     * 成功
     */
    SUCCESS(0, "success"),
    /**
     * 系统错误
     */
    SYSTEM_ERRO(-1, "system_error"),
    /**
     * 资源不存在
     */
    RESOURCE_NOT_EXIST(-2, "resource not exist"),
    /**
     * 副资源不存在
     */
    SUB_RESOURCE_NOT_EXIST(-3, "subResource not exit");

    private final int code;
    private final String msg;

    ResponseConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
