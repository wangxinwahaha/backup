/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.unisinsight.framework.uuv.base.PageParam;
import com.unisinsight.framework.uuv.base.PageResult;
import com.unisinsight.framework.uuv.common.enums.DeletedStatus;
import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.OrgTreeUtils;
import com.unisinsight.framework.uuv.common.utils.UserHandler;
import com.unisinsight.framework.uuv.dto.request.LoginReqDTO;
import com.unisinsight.framework.uuv.dto.request.UserInfoReqDTO;
import com.unisinsight.framework.uuv.dto.request.UpdatePwdDTO;
import com.unisinsight.framework.uuv.dto.request.UserStatusUpdateReqDTO;
import com.unisinsight.framework.uuv.dto.response.*;
import com.unisinsight.framework.uuv.manager.*;
import com.unisinsight.framework.uuv.mapper.*;
import com.unisinsight.framework.uuv.model.*;
import com.unisinsight.framework.uuv.service.UserInfoService;
import com.unisinsight.framework.uuv.common.utils.BeanConvert;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;


/**
 * description 用户实现类
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:16
 * @since 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final static String TITLE_ID = "title_id";
    private final static String ROLE_ID = "role_id";
    private final static String APP_ID = "app_id";
    private final static String ORG_ID = "org_id";
    private final static String POSITION_ID = "position_id";
    private final static String GENDER = "gender";
    private final static String SEARCH = "search";
    private final static String USER_NAME = "user_name";
    private final static String USER_CODE = "user_code";
    private final static String EMAIL = "email";

    @Resource
    private ApplicationMapper applicationMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserPositionMappingMapper userPositionMappingMapper;

    @Resource
    private OrganizationMappingMapper organizationMappingMapper;

    @Resource
    private UserPreferenceMapper userPreferenceMapper;

    @Resource
    private UserPreferenceManager userPreferenceManager;

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private OrganizationManager organizationManager;

    @Resource
    private TitleManager titleManager;

    @Resource
    private DictionaryManager dictionaryManager;

    @Resource
    private PositionManager positionManager;

    @Resource
    private PrivilegeManager privilegeManager;

    @Resource
    private ApplicationManager applicationManager;

    /**
     * 添加用户
     *
     * @param reqDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoResDTO save(UserInfoReqDTO reqDTO) {

        if (!organizationManager.isExist(reqDTO.getOrgId())) {
            throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
        }
        //判断title是否存在
        if (reqDTO.getTitleId() != null) {
            titleIsExist(reqDTO);
        }
        //丰富用户属性
        UserInfoDO userInfoDO = BeanConvert.convert(reqDTO, UserInfoDO.class);
        userInfoDO.setCreatedAt(new Date());
        userInfoDO.setCreatedBy(UserHandler.userCode());
        userInfoDO.setDeleted(DeletedStatus.EXIST.value());
        userInfoDO.setStatus(0);

        if (reqDTO.getPassword() == null) {
            userInfoDO.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        } else {
            userInfoDO.setPassword(DigestUtils.md5DigestAsHex(reqDTO.getPassword().getBytes()));
        }

        //插入用户数据
        userInfoManager.insert(userInfoDO);
        //插入扩展字段
        List<UserPreferenceDO> userPreferenceDOs = reqDTO.getPreferences();
        dealWithPreferences(userPreferenceDOs, userInfoDO.getUserId());

        if (reqDTO.getPositionIds() != null) {
            //插入职位
            Integer[] positionIds = reqDTO.getPositionIds();
            dealWithPositions(positionIds, userInfoDO.getUserId());
        }

        //插入组织
        OrganizationMappingDO organizationMappingDO = new OrganizationMappingDO();
        organizationMappingDO.setUserId(userInfoDO.getUserId());
        organizationMappingDO.setOrgId(reqDTO.getOrgId());
        organizationMappingMapper.insertSelective(organizationMappingDO);

        return BeanConvert.convert(userInfoManager.get(userInfoDO.getUserId()), UserInfoResDTO.class);
    }

    /**
     * 判断title是否存在
     *
     * @param userInfoReq
     */
    private void titleIsExist(UserInfoReqDTO userInfoReq) {
        if (!titleManager.isExist(userInfoReq.getTitleId())) {
            throw FrameworkException.of(ErrorCode.USER_TITLE_NOT_EXIST);
        }
    }

    /**
     * 插入、更新扩展字段共享代码
     *
     * @param userPreferenceDOs
     * @param userId
     */
    private void dealWithPreferences(List<UserPreferenceDO> userPreferenceDOs, Integer userId) {

        List<DictionaryDO> dictionaries = dictionaryManager.getExtendKey("user_info_attr", 0);
        List<String> keys = new ArrayList<>(4);

        //为用户插入空扩展字段
        for (DictionaryDO dictionary : dictionaries) {
            keys.add(dictionary.getKey());
            UserPreferenceDO userPreferenceDO = new UserPreferenceDO();
            userPreferenceDO.setFieldKey(dictionary.getKey());
            userPreferenceDO.setFieldValue(null);
            userPreferenceDO.setUserId(userId);
            userPreferenceManager.insert(userPreferenceDO);
        }
        if (!CollectionUtils.isEmpty(userPreferenceDOs)) {
            for (UserPreferenceDO userPreferenceDO : userPreferenceDOs) {
                if (!keys.contains(userPreferenceDO.getFieldKey())) {
                    //若添加的扩展字段不在字典中，则抛异常
                    throw FrameworkException.of(ErrorCode.PREFERENCE_NOT_EXIST);
                }
                //否则，对之前添加的空字段进行修改
                Condition condition = new Condition(UserPreferenceDO.class);
                Example.Criteria criteria = condition.createCriteria();
                criteria.andEqualTo("userId", userId);
                criteria.andEqualTo("fieldKey", userPreferenceDO.getFieldKey());
                condition.and(criteria);
                userPreferenceMapper.updateByConditionSelective(userPreferenceDO, condition);
            }
        }
    }

    /**
     * 插入、更新职级共享代码
     *
     * @param positionIds
     * @param userId
     */
    private void dealWithPositions(Integer[] positionIds, Integer userId) {
        for (Integer positionId : positionIds) {
            if (!positionManager.isExist(positionId)) {
                throw FrameworkException.of(ErrorCode.USER_POSITION_NOT_EXIST);
            }
            UserPositionMappingDO userPositionMappingDO = new UserPositionMappingDO();
            userPositionMappingDO.setPositionId(positionId);
            userPositionMappingDO.setUserId(userId);
            userPositionMappingMapper.insertSelective(userPositionMappingDO);
        }
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(List<Integer> ids) {

        if (!userInfoManager.checkAllExist(ids)) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }

        for (Integer id : ids) {
            UserInfoDO userInfoDO = new UserInfoDO();
            userInfoDO.setUserId(id);
            userInfoDO.setUpdatedAt(new Date());
            userInfoDO.setUpdatedBy(UserHandler.userCode());
            userInfoManager.updateSelective(userInfoDO);
        }

        userInfoManager.delete(new HashSet<>(ids));
    }

    /**
     * 修改用户
     *
     * @param updateDTO
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoResDTO update(UserInfoReqDTO updateDTO, Integer userId) {
        //判断用户是否存在
        UserInfoDO userInfo = userInfoManager.get(userId);
        if (userInfo == null) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }

        //更新用户信息，更新扩展字段
        UserInfoDO userInfoDO = BeanConvert.convert(updateDTO, UserInfoDO.class);
        userInfoDO.setUserId(userId);
        userInfoDO.setCreatedAt(userInfo.getCreatedAt());
        userInfoDO.setCreatedBy(userInfo.getCreatedBy());
        userInfoDO.setUpdatedAt(new Date());
        userInfoDO.setUpdatedBy(UserHandler.userCode());
        userInfoDO.setStatus(userInfo.getStatus());
        userInfoDO.setUserCode(userInfo.getUserCode());
        userInfoDO.setDeleted(userInfo.getDeleted());
        userInfoDO.setPassword(userInfo.getPassword());

        userInfoManager.update(userInfoDO);

        List<UserPreferenceDO> userPreferenceDOs = updateDTO.getPreferences();
        userPreferenceManager.deleteByUserId(userId);
        dealWithPreferences(userPreferenceDOs, userId);

        Condition delPositionCondition = new Condition(UserPositionMappingDO.class);
        Example.Criteria delPositionCriteria = delPositionCondition.createCriteria();
        delPositionCriteria.andEqualTo("userId", userId);
        delPositionCondition.and(delPositionCriteria);
        userPositionMappingMapper.deleteByCondition(delPositionCondition);
        if (updateDTO.getPositionIds() != null) {
            //更新职位
            Integer[] positionIds = updateDTO.getPositionIds();
            dealWithPositions(positionIds, userId);
        }

        Condition condition = new Condition(OrganizationMappingDO.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId", userId);
        organizationMappingMapper.deleteByCondition(condition);
        //修改组织
        if (updateDTO.getOrgId() != null) {
            if (!organizationManager.isExist(updateDTO.getOrgId())) {
                throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
            }
            OrganizationMappingDO organizationMappingDO = new OrganizationMappingDO();
            organizationMappingDO.setUserId(userId);
            organizationMappingDO.setOrgId(updateDTO.getOrgId());
            organizationMappingMapper.insertSelective(organizationMappingDO);
        }
        return BeanConvert.convert(userInfoManager.get(userInfoDO.getUserId()), UserInfoResDTO.class);
    }

    /**
     * 通过用户id获取用户详情
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findUser(String id) {
        //判断用户是否存在，若不存在，则抛异常，若存在，则查出用户所有信息
        UserDetailInfoDO user;
        try {
            user = findById(Integer.valueOf(id));
        } catch (NumberFormatException e) {
            user = findByUserCode(id);
        } catch (RuntimeException e) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }
        return convertToMap(user);
    }


    private UserDetailInfoDO findById(Integer id) {
        UserDetailInfoDO user = userInfoMapper.selectUserById(id);
        if (user == null) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }
        return user;
    }


    private UserDetailInfoDO findByUserCode(String userCode) {
        //判断用户是否存在，若不存在，则抛异常，若存在，则查出用户所有信息
        UserDetailInfoDO userDetailInfoDO = userInfoMapper.selectUserByUserCode(userCode);
        if (userDetailInfoDO == null) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }

        return userDetailInfoDO;
    }


    private Map<String, Object> convertToMap(UserDetailInfoDO user) {
        //将用户信息装入map中
        Map<String, Object> userInfoMap = new HashMap<>(32);
        userInfoMap.put("user_id", user.getUserId());
        userInfoMap.put("user_code", user.getUserCode());
        userInfoMap.put("user_name", user.getUserName());
        userInfoMap.put("email", user.getEmail());
        userInfoMap.put("gender", user.getGender());
        userInfoMap.put("birthday", user.getBirthday());
        userInfoMap.put("address", user.getAddress());
        userInfoMap.put("work_phone", user.getWorkPhone());
        userInfoMap.put("cell_phone", user.getCellPhone());
        userInfoMap.put("created_by", user.getCreatedBy());
        userInfoMap.put("created_at", user.getCreatedAt());
        userInfoMap.put("updated_by", user.getUpdatedBy());
        userInfoMap.put("updated_at", user.getUpdatedAt());
        userInfoMap.put("status", user.getStatus());
        userInfoMap.put("title_id", user.getTitleId());
        userInfoMap.put("title_name", user.getTitleName());
        userInfoMap.put("title_code", user.getTitleCode());
        userInfoMap.put("org_id", user.getOrgId());
        userInfoMap.put("org_code", user.getOrgCode());
        userInfoMap.put("org_name", user.getOrgName());

        String positionId = user.getPositionId();
        String positionCode = user.getPositionCode();
        String positionName = user.getPositionName();
        //判断用户是否有position
        if (!StringUtils.isEmpty(positionId)) {
            String[] positionIds = positionId.split(",");
            String[] positionCodes = positionCode.split(",");
            String[] positionNames = positionName.split(",");
            ArrayList<PositionResDTO> positions = new ArrayList<>();
            for (int i = 0; i < positionIds.length; i++) {
                PositionResDTO position = new PositionResDTO();
                position.setPositionId(Integer.valueOf(positionIds[i]));
                position.setPositionCode(positionCodes[i]);
                position.setPositionName(positionNames[i]);
                positions.add(position);
            }
            userInfoMap.put("positions", positions);
        } else {
            userInfoMap.put("positions", null);
        }

        String roleId = user.getRoleId();
        String roleCode = user.getRoleCode();
        String roleName = user.getRoleName();
        //判断用户是否有role
        if (!StringUtils.isEmpty(roleId)) {
            String[] roleIds = roleId.split(",");
            String[] roleCodes = roleCode.split(",");
            String[] roleNames = roleName.split(",");
            ArrayList<RoleResDTO> roles = new ArrayList<>();
            for (int i = 0; i < roleIds.length; i++) {
                RoleResDTO roleResDTO = new RoleResDTO();
                roleResDTO.setRoleId(Integer.valueOf(roleIds[i]));
                roleResDTO.setRoleCode(roleCodes[i]);
                roleResDTO.setRoleName(roleNames[i]);
                roles.add(roleResDTO);
            }
            userInfoMap.put("roles", roles);
        }

        //单独查找用户扩展字段
        String extendKey = user.getExtendKey();
        String extendValue = user.getExtendValue();
        List<UserPreferenceResDTO> userPreferences = new ArrayList<>(4);
        if (extendKey != null) {
            String[] extendKeys = extendKey.split(",");
            if (extendValue != null) {
                String[] extendValues = extendValue.split(",");
                for (int j = 0; j < extendKeys.length; j++) {
                    UserPreferenceResDTO userPreference = new UserPreferenceResDTO();
                    userPreference.setFieldKey(extendKeys[j]);
                    userPreference.setFieldValue(extendValues[j]);
                    userPreferences.add(userPreference);
                }
            } else {
                for (String key : extendKeys) {
                    UserPreferenceResDTO userPreference = new UserPreferenceResDTO();
                    userPreference.setFieldKey(key);
                    userPreference.setFieldValue("");
                    userPreferences.add(userPreference);
                }
            }
        }
        userInfoMap.put("preferences", userPreferences);

        return userInfoMap;

    }

    /**
     * 修改用户密码
     *
     * @param updatePwdDTO
     * @param userId
     * @return
     */
    @Override
    public UserInfoResDTO updatePassword(UpdatePwdDTO updatePwdDTO, Integer userId) {
        //判断用户是否存在
        if (!userInfoManager.isExist(userId)) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }
        //判断密码是否错误
        String password = userInfoManager.selectPasswordBy(userId);
        if (!password.equals(DigestUtils.md5DigestAsHex(updatePwdDTO.getOldPassword().getBytes()).toLowerCase())) {
            throw FrameworkException.of(ErrorCode.BASE_PASSWORD_ERROR);
        }

        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setUserId(userId);
        userInfo.setUpdatedAt(new Date());
        userInfo.setUpdatedBy(UserHandler.userCode());
        userInfo.setPassword(DigestUtils.md5DigestAsHex(updatePwdDTO.getNewPassword().getBytes()).toLowerCase());
        userInfoManager.updateSelective(userInfo);

        return BeanConvert.convert(userInfoManager.get(userId), UserInfoResDTO.class);
    }

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
    @Override
    public PageResult<List<Map<String, Object>>> list(Map<String, Object> params,
                                                      Integer pageIndex,
                                                      Integer pageSize,
                                                      String orderFiled,
                                                      String orderRule) {
        //参数校验
        try {
            if (params.containsKey(TITLE_ID) && !"".equals(params.get(TITLE_ID).toString())) {
                Integer.parseInt(params.get("title_id").toString());
            }
            if (params.containsKey(GENDER) && !"".equals(params.get(GENDER).toString())) {
                Integer.parseInt(params.get("gender").toString());
            }

        } catch (Exception e) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }

        if (params.get(SEARCH) != null) {
            if ("'".equals(params.get(SEARCH).toString())) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
            }
            String search = params.get("search").toString().replace("%", "\\%");
            params.put("search", search);
        }
        if (params.get(USER_NAME) != null) {
            if ("'".equals(params.get(USER_NAME).toString())) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
            }
            String userName = params.get("user_name").toString().replace("%", "\\%");
            params.put("user_name", userName);
        }
        if (params.get(USER_CODE) != null) {
            if ("'".equals(params.get(USER_CODE).toString())) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
            }
            String userCode = params.get("user_code").toString().replace("%", "\\%");
            params.put("user_code", userCode);
        }
        if (params.get(EMAIL) != null) {
            if ("'".equals(params.get(EMAIL).toString())) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
            }
            String email = params.get("email").toString().replace("%", "\\%");
            params.put("email", email);
        }


        //获取当前组织及其子组织
        String orgId = null;
        if (params.get(ORG_ID) != null && !"".equals(params.get(ORG_ID).toString())) {
            //获取所有组织
            List<OrganizationExtraDO> orgs = BeanConvert
                    .convertList(organizationManager.selectAll(), OrganizationExtraDO.class);
            List<Integer> orgIdList = OrgTreeUtils
                    .getSubNodes(orgs, Integer.parseInt(params.get("org_id").toString()));
            orgIdList.add(Integer.parseInt(params.get("org_id").toString()));
            orgId = orgIdList.toString().replace("[", "(\'")
                    .replace("]", "\')")
                    .replace(",", "\',\'")
                    .replace(" ", "");
        }

        //将positionId参数转化为List<Integer>
        List<Integer> positionIds = null;
        if (params.get(POSITION_ID) != null && !"".equals(params.get(POSITION_ID).toString())) {
            positionIds = new ArrayList<>();
            for (String val : params.get(POSITION_ID).toString().split(",")) {
                positionIds.add(Integer.parseInt(val));
            }
        }

        //将roleId参数转化为List<Integer>
        List<Integer> roleIds = null;
        if (params.get(ROLE_ID) != null && !"".equals(params.get(ROLE_ID).toString())) {
            roleIds = new ArrayList<>();
            for (String val : params.get(ROLE_ID).toString().split(",")) {
                roleIds.add(Integer.parseInt(val));
            }
        }

        //将appId参数转化为List<Integer>
        List<Integer> appIds = null;
        if (params.get(APP_ID) != null && !"".equals(params.get(APP_ID).toString())) {
            appIds = new ArrayList<>();
            for (String val : params.get(APP_ID).toString().split(",")) {
                appIds.add(Integer.parseInt(val));
            }
        }

        List<UserInfoListDO> userInfoListDOS = userInfoMapper
                .list(params, orgId, positionIds,
                        roleIds, appIds, pageIndex,
                        pageSize, orderFiled, orderRule);
        Integer total = userInfoMapper
                .count(params, orgId, positionIds,
                        roleIds, appIds, pageIndex,
                        pageSize, orderFiled, orderRule);

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < userInfoListDOS.size(); i++) {
            Map<String, Object> userInfoListMap = new HashMap<>(32);
            userInfoListMap.put("user_id", userInfoListDOS.get(i).getUserId());
            userInfoListMap.put("user_code", userInfoListDOS.get(i).getUserCode());
            userInfoListMap.put("user_name", userInfoListDOS.get(i).getUserName());
            userInfoListMap.put("email", userInfoListDOS.get(i).getEmail());
            userInfoListMap.put("gender", userInfoListDOS.get(i).getGender());
            userInfoListMap.put("birthday", userInfoListDOS.get(i).getBirthday());
            userInfoListMap.put("work_phone", userInfoListDOS.get(i).getWorkPhone());
            userInfoListMap.put("cell_phone", userInfoListDOS.get(i).getCellPhone());
            userInfoListMap.put("title_name", userInfoListDOS.get(i).getTitleName());
            userInfoListMap.put("title_code", userInfoListDOS.get(i).getTitleCode());
            userInfoListMap.put("org_code", userInfoListDOS.get(i).getOrgCode());
            userInfoListMap.put("org_name", userInfoListDOS.get(i).getOrgName());
            userInfoListMap.put("status", userInfoListDOS.get(i).getStatus());

            //解析出拓展字段并加入结果集
            String extendKey = userInfoListDOS.get(i).getExtendKey();
            String extendValue = userInfoListDOS.get(i).getExtendValue();
            if (extendKey != null) {
                String[] extendKeys = extendKey.split(",");
                if (extendValue != null) {
                    String[] extendValues = extendValue.split(",");
                    for (int j = 0; j < extendKeys.length; j++) {
                        userInfoListMap.put(extendKeys[j], extendValues[j]);
                    }
                } else {
                    for (String key : extendKeys) {
                        userInfoListMap.put(key, "");
                    }
                }
            }

            list.add(userInfoListMap);
        }

        //包装分页结果
        PageResult<List<Map<String, Object>>> pageResult = new PageResult<>();
        PageParam pageParam = new PageParam();
        pageParam.setTotal(total);
        pageParam.setPageIndex(pageIndex);
        pageParam.setPageSize(pageSize);

        pageResult.setPageParam(pageParam);
        pageResult.setData(list);

        return pageResult;

    }


    /**
     * 更新用户状态
     *
     * @param userStatusUpdateReqDTO
     * @param userId
     * @return
     */
    @Override
    public Integer updateStatus(UserStatusUpdateReqDTO userStatusUpdateReqDTO, Integer userId) {
        if (userStatusUpdateReqDTO.getStatus() == null) {
            throw FrameworkException.of(ErrorCode.USER_STATUS_ERROR);
        }

        if (!userInfoManager.isExist(userId)) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }

        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setStatus(userStatusUpdateReqDTO.getStatus());
        userInfo.setUserId(userId);
        userInfoManager.updateSelective(userInfo);
        return 0;
    }

    /**
     * 获取用户app列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<ApplicationResDTO> getUserApplications(Integer userId) {
        List<ApplicationDO> applicationDOList = applicationMapper.getUserApplications(userId);
        return BeanConvert.convertList(applicationDOList, ApplicationResDTO.class);
    }

    /**
     * 登录
     *
     * @param loginReqDTO
     * @return
     */
    @Override
    public Map<String, Object> login(LoginReqDTO loginReqDTO) {
        Example.Criteria criteria = userInfoManager.buildCriteria();
        criteria.andEqualTo("userCode", loginReqDTO.getUserCode());
        UserInfoDO userInfoDO = userInfoManager.selectOne(criteria);
        if (userInfoDO == null) {
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }
        if (!loginReqDTO.getPassword().equals(userInfoDO.getPassword())) {
            throw FrameworkException.of(ErrorCode.BASE_PASSWORD_ERROR);
        }

        UserDetailInfoDO userDetailInfoDO = userInfoMapper.selectUserByUserCode(loginReqDTO.getUserCode());
        Map<String, Object> userInfoMap = new HashMap<>(32);
        userInfoMap.put("user_id", userInfoDO.getUserId());
        userInfoMap.put("user_name", userInfoDO.getUserName());
        userInfoMap.put("org_id", userDetailInfoDO.getOrgId());
        userInfoMap.put("org_name", userDetailInfoDO.getOrgName());
        userInfoMap.put("org_code", userDetailInfoDO.getOrgCode());

        Example.Criteria preferenceCriteria = userPreferenceManager.buildCriteria();
        preferenceCriteria.andEqualTo("userId", userInfoDO.getUserId());
        List<UserPreferenceDO> preferences = userPreferenceManager.selectAll(preferenceCriteria);
        userInfoMap.put("preferences", BeanConvert.convertList(preferences, UserPreferenceResDTO.class));
        return userInfoMap;
    }

    /**
     * 获取用户权限
     * @param userCode
     * @param appCode
     * @return
     */
    @Override
    public List<UserAppResDTO> getUserPri(String userCode, String appCode) {
        List<ApplicationDO> applications = applicationMapper.getUserAppByCode(userCode);
        if (StringUtils.isEmpty(appCode)) {
            ArrayList<UserAppResDTO> apps = new ArrayList<>(4);
            for (ApplicationDO application : applications) {
                UserAppResDTO userAppResDTO = new UserAppResDTO();
                userAppResDTO.setAppId(application.getAppId());
                userAppResDTO.setAppCode(application.getAppCode());
                userAppResDTO.setAppName(application.getAppName());
                userAppResDTO.setDefaultUrl(application.getDefaultUrl());
                Example.Criteria criteria = privilegeManager.buildCriteria();
                criteria.andEqualTo("appId", userAppResDTO.getAppId());
                userAppResDTO.setMenus(BeanConvert.convertList(privilegeManager.selectAll(criteria), PrivilegeResDTO.class));
                apps.add(userAppResDTO);
            }
            return apps;
        } else {
            //验证app是否存在
            ApplicationDO application = applicationManager.getAppBy(appCode);
            if (application == null) {
                throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
            }
            UserAppResDTO userAppResDTO = BeanConvert.convert(application, UserAppResDTO.class);
            Example.Criteria criteria = privilegeManager.buildCriteria();
            criteria.andEqualTo("appId", userAppResDTO.getAppId());
            userAppResDTO.setMenus(BeanConvert.convertList(privilegeManager.selectAll(criteria), PrivilegeResDTO.class));
            ArrayList<UserAppResDTO> apps = new ArrayList<>(1);
            apps.add(userAppResDTO);
            return apps;
        }
    }
}
