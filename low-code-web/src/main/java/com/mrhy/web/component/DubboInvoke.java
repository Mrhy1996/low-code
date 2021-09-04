package com.mrhy.web.component;

import com.alibaba.fastjson.JSON;
import com.mrhy.api.IInnerService;
import com.mrhy.common.enums.BusinessException;
import com.mrhy.common.enums.ObjectResponse;
import com.mrhy.common.enums.ResponseConstant;
import com.mrhy.model.DubboInnerRequest;
import com.mrhy.model.DubboInnerResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cooper
 * @description 远程调用server层的服务
 * @date 2021/9/4 5:46 下午
 */
@Component
@Log4j2
public class DubboInvoke {
    @DubboReference
    IInnerService innerService;

    /**
     * @description 同步调用远程的dubbo方法
     * @author cooper
     * @date 2021/9/4 5:48 下午
     */
    public ObjectResponse syncInvoke(String resource, String subResource, Object... params) {
        log.info("同步调用远程的dubbo方法，入参为::resource=【{}】,subResource=【{}】,params=【{}】", resource, subResource, params);
        DubboInnerRequest dubboInnerRequest = new DubboInnerRequest();
        dubboInnerRequest.setResource(resource);
        dubboInnerRequest.setSubResource(subResource);
        if (params != null) {
            List<Object> paramList = Arrays.asList(params);
            dubboInnerRequest.setParams(paramList);
        }
        DubboInnerResponse dubboInnerResponse = innerService.query(dubboInnerRequest);
        log.info("同步调用远程的dubbo方法，出参为【{}】", JSON.toJSONString(dubboInnerResponse));
        if (ResponseConstant.SUCCESS.getCode() != dubboInnerResponse.getStatus()) {
            throw new BusinessException(dubboInnerResponse.getMessage());
        }
       return JSON.parseObject(JSON.toJSONString(dubboInnerResponse.getResponse()),ObjectResponse.class);
    }
}
