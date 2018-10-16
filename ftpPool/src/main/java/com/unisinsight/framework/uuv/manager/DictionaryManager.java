/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.model.DictionaryDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 字典manager层
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 9:45
 * @since 1.0
 */
@Component
public class DictionaryManager extends AbstractMapperManager<DictionaryDO> {
    /**
     * 通过appId item key 查询字典条目
     * @param appId  应用系统标识
     * @param item  项目区分
     * @param key
     * @return
     */
    public List<DictionaryDO> getDicByAppIdAndItemAndKey(Integer appId, String item, String key) {
        Example.Criteria criteria = buildCriteria();

        criteria.andEqualTo("item", item);
        criteria.andEqualTo("appId", appId);

        if (key != null) {
            criteria.andEqualTo("key", key);
        }

        return selectAll(criteria);
    }

    /**
     * 获取扩展字段
     * @return
     */
    public List<DictionaryDO> getExtendKey(String item, Integer appId) {
        Example.Criteria criteria = buildCriteria();

        criteria.andEqualTo("item", item);
        criteria.andEqualTo("appId", appId);

        return selectAll(criteria, "key");
    }

}
