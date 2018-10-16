/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.model.ApplicationDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

/**
 * 应用manager层
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 9:44
 * @since 1.0
 */
@Component
public class ApplicationManager extends AbstractMapperManager<ApplicationDO>{

    public ApplicationDO getAppBy(String appCode) {
        Example.Criteria criteria = buildCriteria();

        criteria.andEqualTo("appCode",appCode);

        return selectOne(criteria);
    }
}
