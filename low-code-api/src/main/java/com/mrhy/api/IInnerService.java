package com.mrhy.api;

import com.mrhy.model.DubboInnerRequest;
import com.mrhy.model.DubboInnerResponse;

/**
 * @author cooper
 * @description 低代码平台顶级api
 * @date 2021/8/31 11:12 下午
 */
public interface IInnerService {
    /**
     * @description 内部类
     * @author cooper
     * @date 2021/9/1 12:11 上午
     */
    DubboInnerResponse query(DubboInnerRequest dubboInnerRequest);
}
