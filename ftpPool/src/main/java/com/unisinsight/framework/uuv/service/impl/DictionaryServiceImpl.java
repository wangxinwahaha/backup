/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import com.unisinsight.framework.uuv.dto.request.DictionaryReqDTO;
import com.unisinsight.framework.uuv.dto.response.DictionaryResDTO;
import com.unisinsight.framework.uuv.manager.DictionaryManager;
import com.unisinsight.framework.uuv.mapper.DictionaryMapper;
import com.unisinsight.framework.uuv.model.DictionaryDO;
import com.unisinsight.framework.uuv.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典实现类
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:22
 * @since 1.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryManager dictionaryManager;

    /**
     * 新增字典
     * @param reqDTO
     * @return
     */
    @Override
    public DictionaryResDTO save(DictionaryReqDTO reqDTO) {

        List<DictionaryDO> oldDicList = dictionaryManager.getDicByAppIdAndItemAndKey(reqDTO.getAppId(),
                reqDTO.getItem(), reqDTO.getKey());
        if (!CollectionUtils.isEmpty(oldDicList)) {
            throw FrameworkException.of(ErrorCode.DICTIONARY_EXIST);
        }

        DictionaryDO save = BeanConvert.convert(reqDTO, DictionaryDO.class);
        dictionaryManager.insert(save);

        return BeanConvert.convert(save,DictionaryResDTO.class);
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @Override
    public void deleteById(Integer id) {
        if (dictionaryManager.get(id) == null) {
            throw FrameworkException.of(ErrorCode.DICTIONARY_NOT_EXIST);
        }
        dictionaryManager.delete(id);
    }

    /**
     * 更新字典
     * @param updateDTO
     * @param dictionaryId
     * @return
     */
    @Override
    public DictionaryResDTO update(DictionaryReqDTO updateDTO, Integer dictionaryId) {

        if (dictionaryManager.get(dictionaryId) == null) {
            throw FrameworkException.of(ErrorCode.DICTIONARY_NOT_EXIST);
        }

        List<DictionaryDO> oldItemList = dictionaryManager.getDicByAppIdAndItemAndKey(updateDTO.getAppId(),
                updateDTO.getItem(), updateDTO.getKey());
        if (!CollectionUtils.isEmpty(oldItemList)) {
            DictionaryDO dic = oldItemList.get(0);
            if (!dic.getId().equals(dictionaryId)) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "appId item key 重复");
            }
        }

        DictionaryDO update = BeanConvert.convert(updateDTO, DictionaryDO.class);
        update.setId(dictionaryId);

        dictionaryManager.update(update);

        return BeanConvert.convert(update,DictionaryResDTO.class);

    }

    /**
     *通过Item查询字典条目
     * @param item 项目区分
     * @param appId 应用系统标识
     * @return
     */
    @Override
    public List<DictionaryResDTO> selectDictionaryByItem(String item, Integer appId) {
        return BeanConvert.convertList(dictionaryManager.getDicByAppIdAndItemAndKey(appId, item, null),
                                        DictionaryResDTO.class);
    }

}
