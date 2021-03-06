package com.mrhy.model;

import java.io.Serializable;

public class DubboInnerResponse implements Serializable {
    private Integer status;
    private String message;
    private String clazzName;
    private String methodName;
    private Object response;

    public DubboInnerResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public DubboInnerResponse() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "DubboInnerResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", response=" + response +
                '}';
    }
}
