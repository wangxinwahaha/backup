/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.mapper.OrganizationMapper;
import com.unisinsight.framework.uuv.model.OrganizationDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 组织manager层
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 9:46
 * @since 1.0
 */
@Component
public class OrganizationManager extends AbstractMapperManager<OrganizationDO>{
    @Resource
    private OrganizationMapper mapper;
    public int countAll(){
        Condition condition = new Condition(OrganizationDO.class);
        return mapper.selectCountByCondition(condition);
    }

    public int countByParent(Integer parentId){
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("parentId", parentId);

        return count(criteria);
    }

    public OrganizationDO findByParentAndName(Integer parent, String orgName){
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("parentId", parent);
        criteria.andEqualTo("orgName", orgName);
        return selectOne(criteria);
    }

}
