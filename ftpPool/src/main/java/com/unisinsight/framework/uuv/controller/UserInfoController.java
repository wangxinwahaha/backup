/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.base.PageResult;
import com.unisinsight.framework.uuv.common.utils.StringUtils;
import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.dto.request.LoginReqDTO;
import com.unisinsight.framework.uuv.dto.request.UserInfoReqDTO;
import com.unisinsight.framework.uuv.dto.request.UpdatePwdDTO;
import com.unisinsight.framework.uuv.dto.request.UserStatusUpdateReqDTO;
import com.unisinsight.framework.uuv.dto.response.ApplicationResDTO;
import com.unisinsight.framework.uuv.dto.response.UserAppResDTO;
import com.unisinsight.framework.uuv.dto.response.UserInfoResDTO;
import com.unisinsight.framework.uuv.model.UserInfoDO;
import com.unisinsight.framework.uuv.service.UserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:43
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/users")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 添加用户接口
     * @param userInfoReqDTO
     * @return
     */
    @PostMapping
    public UserInfoResDTO add(@RequestBody @Valid UserInfoReqDTO userInfoReqDTO) {
        if (userInfoReqDTO.getOrgId() == null) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "组织id不存在！");
        }
        if (userInfoReqDTO.getGender() != 0 && userInfoReqDTO.getGender() != 1) {
            throw FrameworkException.of(ErrorCode.USER_GENDER_ERROR);
        }
        return userInfoService.save(userInfoReqDTO);
    }

    /**
     * 删除用户接口
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }
        userInfoService.deleteById(StringUtils.stringToList(userId));
    }

    /**
     * 修改用户接口
     * @param userInfoReqDTO
     * @param userId
     * @return
     */
    @PutMapping("/{user-id}")
    public UserInfoResDTO update(@RequestBody @Valid UserInfoReqDTO userInfoReqDTO,
                                 @PathVariable("user-id") Integer userId) {
        if (userInfoReqDTO.getGender() != 0 && userInfoReqDTO.getGender() != 1) {
            throw FrameworkException.of(ErrorCode.USER_GENDER_ERROR);
        }
        return userInfoService.update(userInfoReqDTO, userId);
    }

    /**
     * 修改密码接口
     * @param updatePwdDTO
     * @param userId
     * @return
     */
    @PatchMapping("/{user_id}/update-passwd")
    public UserInfoResDTO updatePassword(@RequestBody @Valid UpdatePwdDTO updatePwdDTO,
                                         @PathVariable("user_id") Integer userId) {
        return userInfoService.updatePassword(updatePwdDTO, userId);
    }

    /**
     * 获取用户详情接口
     * @param userId
     * @return 用map返回用户属性
     */
    @GetMapping("/{userId}")
    public Map<String, Object> detail(@PathVariable String userId) {
        return userInfoService.findUser(userId);
    }

    /**
     * 获取用户列表
     * 支持分页和字段过滤
     * @param pageIndex
     * @param pageSize
     * @param orderFiled
     * @param orderRule
     * @param params
     * @return
     */
    @GetMapping()
    public PageResult<List<Map<String, Object>>> findAll(@RequestParam(defaultValue = "1", value = "page_index") Integer pageIndex,
                                                         @RequestParam(defaultValue = "10", value = "page_size") Integer pageSize,
                                                         @RequestParam(defaultValue = "user_id", value = "order_filed") String orderFiled,
                                                         @RequestParam(defaultValue = "desc", value = "order_rule") String orderRule,
                                                         @RequestParam Map<String, Object> params) {

        return userInfoService.list(params, pageIndex, pageSize, orderFiled, orderRule);
    }

    /**
     * 获取用户可访问的应用列表
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/applications")
    public List<ApplicationResDTO> getSystemList(@PathVariable Integer userId) {
        return userInfoService.getUserApplications(userId);
    }

    /**
     * 更新用户状态
     * @param userStatusUpdateReqDTO
     * @param userId
     * @return
     */
    @PatchMapping("/{user_id}/update-status")
    public Integer updateStatus(@RequestBody @Valid UserStatusUpdateReqDTO userStatusUpdateReqDTO,
                                @PathVariable("user_id") Integer userId) {
        return userInfoService.updateStatus(userStatusUpdateReqDTO, userId);
    }

    /**
     * 登录
     * @param loginReqDTO
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody @Valid LoginReqDTO loginReqDTO) {
        return userInfoService.login(loginReqDTO);
    }

    @GetMapping("/{userCode}/privilege-menus")
    public List<UserAppResDTO> getUserPri(@PathVariable String userCode, @RequestParam(value = "app_code", required = false) String appCode) {
        return userInfoService.getUserPri(userCode, appCode);
    }
}
