package com.unisinsight.framework.uuv.service.impl;

import com.unisinsight.framework.uuv.common.Constants;
import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.OrgTreeUtils;
import com.unisinsight.framework.uuv.common.utils.UserHandler;
import com.unisinsight.framework.uuv.dto.request.OrganizationPreferenceReqDTO;
import com.unisinsight.framework.uuv.dto.request.OrganizationReqDTO;
import com.unisinsight.framework.uuv.dto.response.OrganizationResDTO;
import com.unisinsight.framework.uuv.manager.DictionaryManager;
import com.unisinsight.framework.uuv.manager.OrganizationManager;
import com.unisinsight.framework.uuv.manager.UserInfoManager;
import com.unisinsight.framework.uuv.mapper.OrganizationMapper;
import com.unisinsight.framework.uuv.mapper.OrganizationPreferenceMapper;
import com.unisinsight.framework.uuv.mapper.OrganizationMappingMapper;
import com.unisinsight.framework.uuv.model.*;
import com.unisinsight.framework.uuv.service.OrganizationService;
import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.*;


/**
 * @author yangjing
 * 2018/08/13.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Resource
    private OrganizationMapper organizationMapper;

    @Resource
    private OrganizationPreferenceMapper preferenceDOMapper;

    @Resource
    private OrganizationMappingMapper orgMappingDOMapper;

    @Resource
    private OrganizationManager organizationManager;

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private DictionaryManager dictionaryManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrganizationResDTO save(OrganizationReqDTO reqDTO) {
        OrganizationDO savOrg = BeanConvert.convert(reqDTO, OrganizationDO.class);

        Integer num = organizationManager.countAll();
        //根节点存在
        if (reqDTO.getParent().equals(Constants.EMPTY_UUID)) {
            if (getRootOrgNode() != null) {
                throw FrameworkException.of(ErrorCode.ORG_ROOT_EXIST);
            }
        } else {
            if (!organizationManager.isExist(reqDTO.getParent())) {
                throw FrameworkException.of(ErrorCode.ORG_PARENT_NOT_EXIST);
            }
        }

        if (organizationManager.findByParentAndName(reqDTO.getParent(), reqDTO.getOrgName()) != null){
            throw FrameworkException.of(ErrorCode.ORG_NAME_EXIST);
        }

        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(3);
        formatter.setGroupingUsed(false);
        String number = formatter.format(++num);
        savOrg.setOrgCode("ORG" + number);

        savOrg.setOrgId(null);
        savOrg.setCreatedAt(new Date());
        savOrg.setCreatedBy(UserHandler.userCode());
        savOrg.setParentId(reqDTO.getParent());
        savOrg.setDisplayOrder(0f);

        organizationManager.insert(savOrg);

        //处理扩展字段
        dealWithPreference(reqDTO, savOrg.getOrgId());
        return BeanConvert.convert(organizationManager.get(savOrg.getOrgId()), OrganizationResDTO.class);
    }


    @Override
    public void deleteById(Integer id) {
        if (!organizationManager.isExist(id)) {
            throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
        }

        if (organizationManager.countByParent(id) != 0) {
            throw FrameworkException.of(ErrorCode.ORG_HAVE_SUBNODE);
        }

        if (countUserByParent(id) != 0){
            throw FrameworkException.of(ErrorCode.ORG_HAVE_SUBNODE, "组织中存在用户");
        }

        deleteOrgMappingByOrgId(id);

        OrganizationDO organizationDO = new OrganizationDO();
        organizationDO.setUpdatedAt(new Date());
        organizationDO.setUpdatedBy(UserHandler.userCode());
        organizationDO.setOrgId(id);
        organizationManager.updateSelective(organizationDO);

        organizationManager.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrganizationResDTO update(OrganizationReqDTO updateDTO, Integer orgId) {
        //入参校验
        OrganizationDO oldOrg = organizationManager.get(orgId);
        if (oldOrg == null) {
            throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
        }

        OrganizationDO oldNameOrg = organizationManager.findByParentAndName(oldOrg.getParentId(), updateDTO.getOrgName());
        if (oldNameOrg != null && !orgId.equals(oldNameOrg.getOrgId())){
            throw FrameworkException.of(ErrorCode.ORG_NAME_EXIST);
        }

        //TODO 优化
        preferenceDOMapper.deleteOrganizationByOrgId(orgId);

        OrganizationDO organization = BeanConvert.convert(updateDTO, OrganizationDO.class);
        organization.setOrgId(orgId);
        organization.setOrgCode(oldOrg.getOrgCode());
        organization.setCreatedAt(oldOrg.getCreatedAt());
        organization.setCreatedBy(oldOrg.getCreatedBy());
        organization.setUpdatedAt(new Date());
        organization.setUpdatedBy(UserHandler.userCode());
        organization.setParentId(oldOrg.getParentId());
        organization.setDisplayOrder(0f);
        organizationManager.update(organization);

        //处理扩展字段
        dealWithPreference(updateDTO, orgId);
        return BeanConvert.convert(organization, OrganizationResDTO.class);
    }

    @Override
    public OrganizationResDTO findById(Integer id) {

        OrganizationDO org = organizationManager.get(id);
        if (!organizationManager.isExist(id)){
            throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
        }
        return BeanConvert.convert(org, OrganizationResDTO.class);
    }

    @Override
    public List<OrganizationResDTO> findAll() {
        return BeanConvert.convertList(organizationManager.selectAll(), OrganizationResDTO.class);
    }

    /**
     * 获取组织架构
     * @param orgId
     * @return List<OrganizationExtraDO>
     */
    @Override
    public List<OrganizationExtraDO> getChildOrg(Integer orgId) {
        List<OrganizationExtraDO> orgs = BeanConvert.convertList(organizationManager.selectAll(), OrganizationExtraDO.class);
        //判断参数是否为0，若为0，则表示获取整个组织架构树
        if (orgId.equals(0)) {
            OrganizationExtraDO rootOrg = OrgTreeUtils.getRoot(orgs);
            List<OrganizationExtraDO> organizations = new ArrayList<>();
            organizations.add(OrgTreeUtils.buildOrgTree(orgs, rootOrg));
            return organizations;
        } else {
            //若不为0，则表示获取当前组织结构，先判断是否存在该组织
            OrganizationDO organizationDO = organizationManager.get(orgId);
            if (organizationDO == null) {
                throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
            }
            OrganizationExtraDO rootOrg = BeanConvert.convert(organizationDO, OrganizationExtraDO.class);

            List<OrganizationExtraDO> organizations = new ArrayList<>();
            organizations.add(OrgTreeUtils.buildOrgTree(orgs, rootOrg));
            return organizations;
        }
    }

    @Override
    public Integer addUsers(Integer orgId, Set<Integer> users) {
        if (!organizationManager.isExist(orgId)){
            throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
        }

        if (!userInfoManager.checkAllExist(users)){
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }

        for (Integer user : users){
            if (!CollectionUtils.isEmpty(getMappingByParentAndSubNodeAndType(orgId, user))){
                throw FrameworkException.of(ErrorCode.USER_EXIST);
            }
        }

        for (Integer user : users){
            OrganizationMappingDO organizationMapping = new OrganizationMappingDO();
            organizationMapping.setUserId(user);
            organizationMapping.setOrgId(orgId);

            orgMappingDOMapper.insertSelective(organizationMapping);
        }


        return users.size();
    }

    @Override
    public void deleteUser(Integer orgId, Integer userId){
        if (CollectionUtils.isEmpty(getByParentAndSubNode(orgId, userId))){
            throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
        }

        //TODO 优化
        orgMappingDOMapper.deleteByTypeAndOrgIdAndSubId(orgId, userId);
    }

    @Override
    public int moveNode(Integer target, List<Integer> currentIds, boolean isMoveOrg){
        if (CollectionUtils.isEmpty(currentIds)){
            throw FrameworkException.of(ErrorCode.ORG_MOVE_ERROR, "currentIds节点不能为空");
        }

        if (!organizationManager.isExist(target)){
            throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
        }

        if (isMoveOrg){
            if (currentIds.size() > 1){
                throw FrameworkException.of(ErrorCode.ORG_MOVE_ERROR, "移动组织currentIds不能超过1个");
            }

            if (currentIds.contains(target)){
                throw FrameworkException.of(ErrorCode.ORG_MOVE_ERROR, "target和currentId不能相同");
            }

            if (!organizationManager.isExist(currentIds.get(0))){
                throw FrameworkException.of(ErrorCode.ORG_MOVE_ERROR, "currentId不存在");
            }

            if (checkTargetIsSubNode(target, currentIds.get(0))){
                throw FrameworkException.of(ErrorCode.ORG_MOVE_ERROR, "target不能为currentId子节点");
            }

            move(target, currentIds, true);

        } else {
            if (userInfoManager.checkAllExist(currentIds)){
                throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
            }

            move(target, currentIds, false);
        }

        return currentIds.size();
    }

    private void move(Integer target, List<Integer> currentIds, boolean isMoveOrg){

        deleteMappingBySubNode(currentIds);

        for (Integer subId : currentIds){
            OrganizationMappingDO orgMapping = new OrganizationMappingDO();
            orgMapping.setOrgId(target);
            orgMapping.setUserId(subId);

            orgMappingDOMapper.insertSelective(orgMapping);
        }
    }

    private int countUserByParent(Integer parent){
        Example example = new Example(OrganizationMappingDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgId", parent);
        Condition condition = new Condition(OrganizationMappingDO.class);
        condition.and(criteria);

        return orgMappingDOMapper.selectCountByCondition(condition);
    }

    private List<OrganizationMappingDO> getByParentAndSubNode(Integer parent, Integer subNode){
        Example example = new Example(OrganizationMappingDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgId", parent);
        criteria.andEqualTo("subId", subNode);
        Condition condition = new Condition(OrganizationMappingDO.class);
        condition.and(criteria);

        return orgMappingDOMapper.selectByCondition(condition);
    }

    private List<OrganizationMappingDO> getMappingByParentAndSubNodeAndType(Integer parent, Integer node){
        Example example = new Example(OrganizationMappingDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgId", parent);
        criteria.andEqualTo("subId", node);
        Condition condition = new Condition(OrganizationMappingDO.class);
        condition.and(criteria);

        return orgMappingDOMapper.selectByCondition(condition);
    }


    private void deleteMappingBySubNode(List<Integer> currentIds){
        Example example = new Example(OrganizationMappingDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("subId", currentIds);
        Condition condition = new Condition(OrganizationMappingDO.class);
        condition.and(criteria);

        orgMappingDOMapper.deleteByCondition(condition);
    }

    private boolean checkTargetIsSubNode(Integer target, Integer currentId){
        List<OrganizationExtraDO> allOrg = organizationMapper.getAllOrgInfo();
        List<Integer> subIds = OrgTreeUtils.getSubNodes(allOrg, currentId);
        return subIds.contains(target);
    }

    private void dealWithPreference(OrganizationReqDTO orgDTO, Integer orgId){
        //查询所有扩展字段
        List<DictionaryDO> dictionaryList = dictionaryManager.getExtendKey("organization_attr", Constants.EMPTY_UUID);

        Set<String> dbKeySet = new HashSet<>();
        for (DictionaryDO dic : dictionaryList){
            dbKeySet.add(dic.getKey());
        }

        //对update preference中数据通过field_key进行去重
        Map<String, OrganizationPreferenceReqDTO> preferenceMap = new HashMap<>(4);

        if (orgDTO.getPreference() != null) {
            for (OrganizationPreferenceReqDTO pre : orgDTO.getPreference()) {

                String key = pre.getFieldKey();
                if (!dbKeySet.contains(key)) {
                    throw FrameworkException.of(ErrorCode.PREFERENCE_NOT_EXIST);
                }

                preferenceMap.put(pre.getFieldKey(), pre);
            }
        }

        //保存所有扩展字段
        List<OrganizationPreferenceReqDTO> savePreferenceList = new ArrayList<>();
        for (String key : dbKeySet){
            if (preferenceMap.get(key) == null){
                OrganizationPreferenceReqDTO preferenceTmp = new OrganizationPreferenceReqDTO();
                preferenceTmp.setOrgId(orgId);
                preferenceTmp.setFieldKey(key);
                preferenceTmp.setFieldValue(null);
                savePreferenceList.add(preferenceTmp);
            }
            else {
                savePreferenceList.add(preferenceMap.get(key));
            }
        }

        for (OrganizationPreferenceReqDTO req : savePreferenceList) {
            req.setOrgId(orgId);
            preferenceDOMapper.insertSelective(BeanConvert.convert(req, OrganizationPreferenceDO.class));
        }

    }

    private OrganizationDO getRootOrgNode(){
        Example.Criteria criteria = organizationManager.buildCriteria();
        criteria.andEqualTo("parentId", 0);
        return organizationManager.selectOne(criteria);
    }

    @Override
    public Map<String, Object> findOrgById(Integer id) {

        Map<String, Object> orgInfoMap = new HashMap<>(8);

        OrganizationDO orgInfoDO = organizationManager.get(id);
        if (orgInfoDO == null) {
            throw FrameworkException.of(ErrorCode.ORG_NOT_EXIST);
        }

        orgInfoMap.put("org_id", orgInfoDO.getOrgId());
        orgInfoMap.put("org_code", orgInfoDO.getOrgCode());
        orgInfoMap.put("org_name", orgInfoDO.getOrgName());
        orgInfoMap.put("created_by", orgInfoDO.getCreatedBy());
        orgInfoMap.put("created_at", orgInfoDO.getCreatedAt());
        orgInfoMap.put("updated_by", orgInfoDO.getUpdatedBy());
        orgInfoMap.put("updated_at", orgInfoDO.getUpdatedAt());
        orgInfoMap.put("address", orgInfoDO.getAddress());
        orgInfoMap.put("description", orgInfoDO.getDescription());

        List<OrganizationPreferenceDO> orgPreInfoList = selectAllPreference(id);

        for (OrganizationPreferenceDO preference : orgPreInfoList){
            if (preference == null){
                return orgInfoMap;
            }
            String fieldKey = preference.getFieldKey();
            String fieldValue = preference.getFieldValue();
            orgInfoMap.put(fieldKey,fieldValue);
        }

        return orgInfoMap;
    }

    private List<OrganizationPreferenceDO> selectAllPreference(Integer orgId){
        Condition condition = new Condition(OrganizationPreferenceDO.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orgId", orgId);

        condition.and(criteria);
        return preferenceDOMapper.selectByCondition(condition);
    }

    private void deleteOrgMappingByOrgId(Integer orgId){
        Condition condition = new Condition(OrganizationMappingDO.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orgId", orgId);
        condition.and(criteria);

        orgMappingDOMapper.deleteByCondition(condition);
    }
}
