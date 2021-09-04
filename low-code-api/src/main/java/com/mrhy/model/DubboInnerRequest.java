package com.mrhy.model;

import java.io.Serializable;
import java.util.List;

public class DubboInnerRequest  implements Serializable {
    private String resource;
    private String subResource;
    private List<Object>params;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getSubResource() {
        return subResource;
    }

    public void setSubResource(String subResource) {
        this.subResource = subResource;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "DubboInnerRequest{" +
                "resource='" + resource + '\'' +
                ", subResource='" + subResource + '\'' +
                ", params=" + params +
                '}';
    }
}
