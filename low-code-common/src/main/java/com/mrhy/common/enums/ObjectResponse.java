package com.mrhy.common.enums;

import java.io.Serializable;

/**
 * @description 业务测响应结果
 * @author cooper
 * @date 2021/9/4 9:01 下午
 */
public class ObjectResponse implements Serializable {

    private static final long serialVersionUID = -1860767323285144762L;
    private Integer returnCode;
    private String description;
    private Object result;

    public ObjectResponse() {
        this.returnCode = OperationFlag.SUCCESS.getReturnCode();
        this.description = OperationFlag.SUCCESS.getDescription();
    }

    public ObjectResponse(Integer returnCode) {
        this(returnCode, (String) null);
    }

    public ObjectResponse(Object result) {
        this.returnCode = OperationFlag.SUCCESS.getReturnCode();
        this.description = OperationFlag.SUCCESS.getDescription();
        this.result = result;
    }

    public ObjectResponse(Integer returnCode, String description) {
        this(returnCode, description, null);
    }

    public ObjectResponse(Integer returnCode, String description, Object result) {
        this.returnCode = returnCode;
        this.description = description;
        this.result = result;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

