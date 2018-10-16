/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;
import com.unisinsight.framework.uuv.base.PageResult;
import com.unisinsight.framework.uuv.dto.request.LoginReqDTO;
import com.unisinsight.framework.uuv.dto.request.UserInfoReqDTO;
import com.unisinsight.framework.uuv.dto.request.UpdatePwdDTO;
import com.unisinsight.framework.uuv.dto.request.UserStatusUpdateReqDTO;
import com.unisinsight.framework.uuv.dto.response.UserAppResDTO;
import com.unisinsight.framework.uuv.dto.response.UserInfoResDTO;
import com.unisinsight.framework.uuv.dto.response.ApplicationResDTO;
import com.unisinsight.framework.uuv.model.UserInfoDO;

import java.util.List;
import java.util.Map;


/**
 * description 用户接口
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:06
 * @since 1.0
 */
public interface UserInfoService {

    /**
     * 新增
     *
     * @param reqDTO
     * @return
     */
    UserInfoResDTO save(UserInfoReqDTO reqDTO);


    /**
     * 通过主键删除
     *
     * @param ids
     * @return
     */
    void deleteById(List<Integer> ids);

    /**
     * 更新
     *
     * @param updateDTO
     * @param userId
     * @return
     */
    UserInfoResDTO update(UserInfoReqDTO updateDTO, Integer userId);

    /**
     * 通过ID查找
     *
     * @param id
     * @return
     */
    Map<String, Object> findUser(String id);

    /**
     * 通过主键重置密码
     *
     * @param updatePwdDTO
     * @param userId
     * @return
     */
    UserInfoResDTO updatePassword(UpdatePwdDTO updatePwdDTO, Integer userId);

    /**
     * 获取用户列表
     * 支持分页和字段过滤
     *
     * @param params
     * @param pageIndex
     * @param pageSize
     * @param orderFiled
     * @param orderRule
     * @return
     */
    PageResult<List<Map<String, Object>>> list(Map<String, Object> params,
                                               Integer pageIndex,
                                               Integer pageSize,
                                               String orderFiled,
                                               String orderRule);

    /**
     * 获取系统列表
     *
     * @param userId
     * @return
     */
    List<ApplicationResDTO> getUserApplications(Integer userId);

    /**
     * 更新用户状态
     *
     * @param userStatusUpdateReqDTO
     * @param userId
     * @return
     */
    Integer updateStatus(UserStatusUpdateReqDTO userStatusUpdateReqDTO, Integer userId);

    /**
     * 登录
     *
     * @param loginReqDTO
     * @return
     */
    Map<String, Object> login(LoginReqDTO loginReqDTO);

    /**
     * 获取用户可访问app与权限
     * @param userCode
     * @param appCode
     * @return
     */
    List<UserAppResDTO> getUserPri(String userCode, String appCode);
}