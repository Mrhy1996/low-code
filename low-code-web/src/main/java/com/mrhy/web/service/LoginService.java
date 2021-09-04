package com.mrhy.web.service;

import com.alibaba.fastjson.JSON;
import com.mrhy.api.IInnerService;
import com.mrhy.common.enums.ObjectResponse;
import com.mrhy.common.model.UserInfo;
import com.mrhy.model.DubboInnerRequest;
import com.mrhy.model.DubboInnerResponse;
import com.mrhy.web.component.DubboInvoke;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author cooper
 * @description 登录
 * @date 2021/9/4 5:40 下午
 */
@Service
@Log4j2
public class LoginService {
    final DubboInvoke dubboInvoke;

    public LoginService(DubboInvoke dubboInvoke) {
        this.dubboInvoke = dubboInvoke;
    }

    /**
     * @description 注册
     * @author cooper
     * @date 2021/9/4 5:42 下午
     */
    public ObjectResponse register(UserInfo userInfo) {
        return dubboInvoke.syncInvoke("userInfoServiceImpl", "register", userInfo);
    }

    /**
     * @description 登录
     * @author cooper
     * @date 2021/9/4 6:18 下午
     */
    public ObjectResponse login(String userName, String password) {
        return dubboInvoke.syncInvoke("userInfoServiceImpl", "login", userName, password);
    }
}
