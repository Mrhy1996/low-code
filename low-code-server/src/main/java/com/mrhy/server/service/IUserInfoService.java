package com.mrhy.server.service;

import com.mrhy.common.enums.ObjectResponse;
import com.mrhy.common.model.UserInfo;
import com.mrhy.server.entity.UserInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基础信息表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-09-04
 */
public interface IUserInfoService extends IService<UserInfoEntity> {

    /**
     * @description 注册
     * @author cooper
     * @date 2021/9/4 7:48 下午
     */
    ObjectResponse register(UserInfo userInfo);

    /**
     * @description 登录
     * @author cooper
     * @date 2021/9/4 7:49 下午
     */
    ObjectResponse login(String userName, String password);
}
