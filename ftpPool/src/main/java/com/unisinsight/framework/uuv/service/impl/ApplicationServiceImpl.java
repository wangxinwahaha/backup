/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unisinsight.framework.uuv.base.PageParam;
import com.unisinsight.framework.uuv.base.PageResult;
import com.unisinsight.framework.uuv.base.PageResultUtil;
import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import com.unisinsight.framework.uuv.common.utils.UserHandler;
import com.unisinsight.framework.uuv.dto.request.ApplicationReqDTO;
import com.unisinsight.framework.uuv.dto.response.ApplicationResDTO;
import com.unisinsight.framework.uuv.dto.response.PrivilegeResDTO;
import com.unisinsight.framework.uuv.manager.ApplicationManager;
import com.unisinsight.framework.uuv.manager.PrivilegeManager;
import com.unisinsight.framework.uuv.manager.RoleManager;
import com.unisinsight.framework.uuv.mapper.ApplicationCategoryMapper;
import com.unisinsight.framework.uuv.mapper.PrivilegeMapper;
import com.unisinsight.framework.uuv.model.ApplicationCategoryDO;
import com.unisinsight.framework.uuv.model.ApplicationDO;
import com.unisinsight.framework.uuv.model.PrivilegeDO;
import com.unisinsight.framework.uuv.service.ApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 应用实现类
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:21
 * @since 1.0
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final static String DATATYPE = "data_type";

    private final static String ZERO = "0";

    private final static String ONE = "1";

    @Resource
    private ApplicationCategoryMapper appCategoryMapper;

    @Resource
    private PrivilegeMapper privilegeMapper;

    @Resource
    private PrivilegeManager privilegeManager;

    @Resource
    private ApplicationManager applicationManager;

    @Resource
    private RoleManager roleManager;

    /**
     * 新增应用
     *
     * @param reqDTO
     * @return
     */
    @Override
    public ApplicationResDTO save(ApplicationReqDTO reqDTO) {

        //判断应用是否存在
        if (applicationManager.getAppBy(reqDTO.getAppCode()) != null) {
            throw FrameworkException.of(ErrorCode.APP_EXIST);
        }
        //判断应用类型是否存在
        ApplicationCategoryDO appCategory = appCategoryMapper.selectByPrimaryKey(reqDTO.getCategoryId());
        if (appCategory == null) {
            throw FrameworkException.of(ErrorCode.APP_CATEGORY_NOT_EXIST);
        }
        //设置创建时间，更新时间
        ApplicationDO save = BeanConvert.convert(reqDTO, ApplicationDO.class);
        save.setCreateAt(new Date());
        save.setUpdatedAt(new Date());
        save.setCreatedBy(UserHandler.userCode());
        save.setStatus(0);
        applicationManager.insert(save);

        return BeanConvert.convert(save, ApplicationResDTO.class);
    }

    /**
     * 根据应用ID删除应用，将deleted字段置为-1。
     *
     * @param appId
     * @return
     */
    @Override
    public void deleteById(Integer appId) {
        //判断应用是否存在
        ApplicationDO deleteApp = applicationManager.get(appId);
        if (deleteApp == null) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }
        //删除应用下的角色
        roleManager.deleteRoleBy(appId);
        //删除应用下的权限
        privilegeManager.deletePriBy(appId);
        //删除应用
        applicationManager.delete(deleteApp);

    }

    /**
     * 更新应用
     *
     * @param appReq
     * @param appId
     * @return
     */
    @Override
    public ApplicationResDTO update(ApplicationReqDTO appReq, Integer appId) {
        //判断应用是否存在
        ApplicationDO oldApp = applicationManager.get(appId);
        if (oldApp == null) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        //若修改了code，则抛异常
        if (appReq.getAppCode() != null && !oldApp.getAppCode().equals(appReq.getAppCode())) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "您无权修改应用code！");
        }

        //当修改应用类型时，判断应用类型是否存在
        if (appReq.getCategoryId() != null) {
            ApplicationCategoryDO appCategory = appCategoryMapper.selectByPrimaryKey(appReq.getCategoryId());
            if (appCategory == null) {
                throw FrameworkException.of(ErrorCode.APP_CATEGORY_NOT_EXIST);
            }
        }
        //设置原始值
        ApplicationDO update = BeanConvert.convert(appReq, ApplicationDO.class);
        update.setAppId(appId);
        if (appReq.getAppCode() == null) {
            update.setAppCode(oldApp.getAppCode());
        }
        if (appReq.getAppName() == null) {
            update.setAppName(oldApp.getAppName());
        }
        if (appReq.getDefaultUrl() == null) {
            update.setDefaultUrl(oldApp.getDefaultUrl());
        }

        if (appReq.getCategoryId() == null) {
            update.setCategoryId(oldApp.getCategoryId());
        }
        update.setCreateAt(oldApp.getCreateAt());
        update.setDeleted(oldApp.getDeleted());
        update.setCreatedBy(oldApp.getCreatedBy());
        update.setUpdatedAt(new Date());
        update.setUpdatedBy(UserHandler.userCode());
        applicationManager.update(update);

        return BeanConvert.convert(update, ApplicationResDTO.class);
    }

    @Override
    public ApplicationResDTO getAppById(Integer appId) {
        ApplicationDO applicationDO = applicationManager.get(appId);
        if (applicationDO == null) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }
        return BeanConvert.convert(applicationDO, ApplicationResDTO.class);

    }

    @Override
    public List<ApplicationResDTO> findAll() {
        List<ApplicationDO> applicationDOList = applicationManager.selectAll();
        return BeanConvert.convertList(applicationDOList, ApplicationResDTO.class);

    }

    @Override
    public PageResult<List<PrivilegeResDTO>> getPrivilegeOrderList(Integer appId,
                                                                   Integer roleId,
                                                                   boolean reversal,
                                                                   Map<String, Object> params,
                                                                   PageParam pageParam) {

        if (0 == roleId) {
            return selectAllPrivilege(appId, params, pageParam);
        } else {
            return selectAppRolePri(appId, roleId, reversal, params, pageParam);
        }
    }

    private PageResult<List<PrivilegeResDTO>> selectAllPrivilege(Integer appId, Map<String, Object> params, PageParam pageParam) {
        Integer dataType = null;
        String dataTypeStr = null;
        if (params.get(DATATYPE) != null) {
            dataTypeStr = params.get(DATATYPE).toString();
        }

        if (ZERO.equals(dataTypeStr)) {
            dataType = 0;
        }
        if (ONE.equals(dataTypeStr)) {
            dataType = 1;
        }

        Page page = PageHelper.startPage(pageParam.getPageIndex(), pageParam.getPageSize(),
                pageParam.getOrderFiled() + " " + pageParam.getOrderRule());

        List<PrivilegeDO> privilegeDOList = privilegeMapper.getPrivilegeByAppId(appId, dataType, params.get("search"));
        List<PrivilegeResDTO> list = BeanConvert.convertList(privilegeDOList, PrivilegeResDTO.class);

        return PageResultUtil.genPageResult(list, page);
    }

    private PageResult<List<PrivilegeResDTO>> selectAppRolePri(Integer appId, Integer roleId,
                                                               boolean reversal, Map<String, Object> param,
                                                               PageParam pageParam) {

        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        if (!roleManager.isExist(roleId)) {
            throw FrameworkException.of(ErrorCode.ROLE_NOT_EXIST);
        }

        Page page = PageHelper.startPage(pageParam.getPageIndex(), pageParam.getPageSize(),
                pageParam.getOrderFiled() + " " + pageParam.getOrderRule());

        List<PrivilegeDO> privilegeList;
        if (reversal) {
            privilegeList = privilegeMapper.selectAppRoleNotPri(appId, roleId, param.get("search"));
        } else {
            privilegeList = privilegeMapper.selectAppRolePri(appId, roleId, param.get("search"));
        }
        List<PrivilegeResDTO> list = BeanConvert.convertList(privilegeList, PrivilegeResDTO.class);

        return PageResultUtil.genPageResult(list, page);
    }

    @Override
    public PageResult<List<ApplicationResDTO>> getAppOrderList(Map<String, Object> params,
                                                               Integer pageIndex,
                                                               Integer pageSize,
                                                               String orderFiled,
                                                               String orderRule) {

        Page page = PageHelper.startPage(pageIndex, pageSize, orderFiled + " " + orderRule);
        List<ApplicationDO> applicationDOList = applicationManager.selectAll();
        List<ApplicationResDTO> list = BeanConvert.convertList(applicationDOList, ApplicationResDTO.class);

        return PageResultUtil.genPageResult(list, page);
    }
}
