package com.mrhy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mrhy.common.enums.ObjectResponse;
import com.mrhy.common.enums.OperationFlag;
import com.mrhy.common.model.UserInfo;
import com.mrhy.server.entity.UserInfoEntity;
import com.mrhy.server.mapper.UserInfoMapper;
import com.mrhy.server.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基础信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-09-04
 */
@Service
@Log4j2
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements IUserInfoService {

    /**
     * @param userInfo 用户实体类
     * @description 注册
     * @author cooper
     * @date 2021/9/4 7:48 下午
     */
    @Override
    public ObjectResponse register(UserInfo userInfo) {
        log.info("用户进行注册,入参为：【{}】", JSON.toJSONString(userInfo));
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userInfo.getUserName());
        if (getOne(queryWrapper) == null) {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            BeanUtils.copyProperties(userInfo, userInfoEntity);
            save(userInfoEntity);
            return new ObjectResponse("注册成功");
        } else {
            return new ObjectResponse(OperationFlag.BUSINESS_ERROR.getReturnCode(), "用户名已经存在！");
        }
    }

    /**
     * @param userName 账号
     * @param password 密码
     * @description 登录
     * @author cooper
     * @date 2021/9/4 7:49 下午
     */
    @Override
    public ObjectResponse login(String userName, String password) {
        log.info("用户进行登录，用户名为【{}】", userName);
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        UserInfoEntity userInfoEntity = getOne(queryWrapper);
        ObjectResponse objectResponse = new ObjectResponse();
        if (userInfoEntity == null) {
            log.error("账户【{}】不存在", userName);
            objectResponse.setReturnCode(OperationFlag.BUSINESS_ERROR.getReturnCode());
            objectResponse.setDescription("账户不存在");
        } else {
            if (!password.equals(userInfoEntity.getPassword())) {
                log.error("账户【{}】的密码不正确", userName);
                objectResponse.setReturnCode(OperationFlag.BUSINESS_ERROR.getReturnCode());
                objectResponse.setDescription("账户或者密码不正确");
            } else {
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(userInfoEntity, userInfo);
                objectResponse.setReturnCode(OperationFlag.SUCCESS.getReturnCode());
                objectResponse.setDescription(OperationFlag.SUCCESS.getDescription());
                objectResponse.setResult(userInfo);
            }
        }
        return objectResponse;
    }
}
