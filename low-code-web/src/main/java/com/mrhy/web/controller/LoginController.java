package com.mrhy.web.controller;

import com.alibaba.fastjson.JSON;
import com.mrhy.api.IInnerService;
import com.mrhy.common.enums.ObjectResponse;
import com.mrhy.common.model.UserInfo;
import com.mrhy.model.DubboInnerRequest;
import com.mrhy.model.DubboInnerResponse;
import com.mrhy.web.service.LoginService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author cooper
 * @description 登录
 * @date 2021/8/30 12:00 上午
 */
@RestController
@RequestMapping("/")
@Log4j2
public class LoginController {

    LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/login")
    @ApiOperation("登录")
    public ObjectResponse login(@RequestParam String userName, @RequestParam String password) {
        log.info("用户访问登录接口，userName=【{}】,password=【{}】", userName, password);
        return loginService.login(userName, password);
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public ObjectResponse register(@RequestBody UserInfo userInfo){
        log.info("用户访问注册接口【{}】",JSON.toJSONString(userInfo));
        return loginService.register(userInfo);
    }


}
