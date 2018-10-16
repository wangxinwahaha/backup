/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.dto.request.DictionaryReqDTO;
import com.unisinsight.framework.uuv.dto.response.DictionaryResDTO;
import com.unisinsight.framework.uuv.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 字典Controller层
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/dictionarys")
public class DictionaryController {
    @Resource
    private DictionaryService dictionaryService;

    /**
     * 添加字典
     *
     * @param dictionaryReqDTO
     * @return
     */
    @PostMapping()
    public DictionaryResDTO addDictionary(@RequestBody @Valid DictionaryReqDTO dictionaryReqDTO) {
        return dictionaryService.save(dictionaryReqDTO);
    }

    /**
     * 删除字典
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteDictionary(@PathVariable Integer id) {
        dictionaryService.deleteById(id);
    }

    /**
     * 更新字典
     *
     * @param dictionaryReqDTO
     * @param id
     */
    @PutMapping("/{id}")
    public DictionaryResDTO updateDictionary(@RequestBody DictionaryReqDTO dictionaryReqDTO,
                                             @PathVariable("id") Integer id) {
        return dictionaryService.update(dictionaryReqDTO, id);
    }

    /**
     * 通过item查询key-value
     *
     * @param item  项目区分
     * @param appId 应用系统标识
     * @return key-value comments（备注）列表
     */
    @GetMapping("/item/{item}")
    public List<DictionaryResDTO> selectDictionaryByItem(@PathVariable String item,
                                                         @RequestParam(value = "app_id") Integer appId) {
        return dictionaryService.selectDictionaryByItem(item, appId);
    }
}
