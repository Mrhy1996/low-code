package com.mrhy.server.dubbo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mrhy.api.IInnerService;
import com.mrhy.common.enums.ResponseConstant;
import com.mrhy.model.DubboInnerRequest;
import com.mrhy.model.DubboInnerResponse;
import com.mrhy.server.utils.SpringUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author cooper
 * @description dubbo内部实现类
 * @date 2021/9/4 4:00 下午
 */
@DubboService
@Log4j2
@Component
public class InnerServiceImpl implements IInnerService {
    @Override
    public DubboInnerResponse query(DubboInnerRequest dubboInnerRequest) {
        log.info("dubbo层收到request：{}", JSON.toJSONString(dubboInnerRequest));
        DubboInnerResponse dubboInnerResponse = new DubboInnerResponse(ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getMsg());
        String resource = dubboInnerRequest.getResource();
        String subResource = dubboInnerRequest.getSubResource();
        List<Object> params = dubboInnerRequest.getParams();
        Object bean;
        try {
            //        反射获取类
            bean = SpringUtils.getBean(resource);
        } catch (NoSuchBeanDefinitionException e) {
            dubboInnerResponse.setStatus(ResponseConstant.RESOURCE_NOT_EXIST.getCode());
            dubboInnerResponse.setMessage(ResponseConstant.RESOURCE_NOT_EXIST.getMsg());
            log.error("没有找到bean，dubbo层返回：{}", JSON.toJSONString(dubboInnerResponse, SerializerFeature.WriteDateUseDateFormat));
            return dubboInnerResponse;
        }
        Class<?> clazz = bean.getClass();
//        返回值
        dubboInnerResponse.setClazzName(clazz.getName());
        Method[] ms = clazz.getDeclaredMethods();
        if (ms.length == 0) {
            dubboInnerResponse.setStatus(ResponseConstant.SUB_RESOURCE_NOT_EXIST.getCode());
            dubboInnerResponse.setMessage(ResponseConstant.SUB_RESOURCE_NOT_EXIST.getMsg());
        }
        Object o = null;
        boolean isFind = false;
        for (Method m : ms) {
            if (m.getName().equalsIgnoreCase(subResource)) {
                isFind = true;
                try {
                    if (params == null) {
                        o = m.invoke(bean);
                    } else {
                        o = m.invoke(bean, params.toArray());
                    }
                } catch (Exception e) {
                    dubboInnerResponse.setStatus(ResponseConstant.SYSTEM_ERRO.getCode());
                    dubboInnerResponse.setMessage(ResponseConstant.SYSTEM_ERRO.getMsg());
                    log.error("反射出错，dubbo层返回：{}", JSON.toJSONString(dubboInnerResponse, SerializerFeature.WriteDateUseDateFormat), e);
                    return dubboInnerResponse;
                }
            }
        }
//        第二次检查看看是否依旧为null；
        if (!isFind) {
            dubboInnerResponse.setStatus(ResponseConstant.SUB_RESOURCE_NOT_EXIST.getCode());
            dubboInnerResponse.setMessage(ResponseConstant.SUB_RESOURCE_NOT_EXIST.getMsg());
        } else {
            dubboInnerResponse.setMethodName(dubboInnerRequest.getSubResource());
            dubboInnerResponse.setResponse(o);
        }
        log.info("dubbo层返回：{}", JSON.toJSONString(dubboInnerResponse, SerializerFeature.WriteDateUseDateFormat));

        return dubboInnerResponse;
    }
}
