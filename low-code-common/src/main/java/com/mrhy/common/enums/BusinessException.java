package com.mrhy.common.enums;

/**
 * @description 业务异常
 * @author cooper
 * @date 2021/7/7 2:52 下午
 */
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
        this.errorCode = OperationFlag.BUSINESS_ERROR.getReturnCode();
    }

    public BusinessException(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
