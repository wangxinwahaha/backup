/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unisinsight.framework.uuv.base.ListParams;
import com.unisinsight.framework.uuv.base.PageParam;
import com.unisinsight.framework.uuv.base.PageResult;
import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import com.unisinsight.framework.uuv.manager.AbstractMapperManager;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * description 基础接口
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/7 10:14
 * @since 1.0
 */
public class BaseService<T, M> {
    @Autowired(required = false)
    private AbstractMapperManager<T> manager;

    private Class modelClass;
    private Class dtoClass;

    public BaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
        dtoClass =   (Class<M>) pt.getActualTypeArguments()[1];
    }

    public int add(T t){
        addBefore(t);
        return manager.insert(t);
    }

    public int update(T t){
        updateBefore(t);
        return manager.update(t);
    }

    public void delete(Integer id){
        deleteBefore(id);
        manager.delete(id);
    }

    public PageResult<List<M>> list(ListParams<T> listParams){
        PageResult<List<M>> result = new PageResult<>();
        Page page = PageHelper.startPage(listParams.getPageIndex(), listParams.getPageSize(), listParams.isCount());

        List<T> list = manager.selectAll(listParams.getCondition());
        List<M> resultList = new ArrayList<>();
        for (T t : list){
            M m = (M)BeanConvert.convert(t, dtoClass);
            resultList.add(m);
        }

        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(listParams.getPageIndex());
        pageParam.setPageSize(listParams.getPageSize());
        pageParam.setTotal((int)page.getTotal());

        result.setPageParam(pageParam);
        result.setData(resultList);

        PageHelper.clearPage();
        return result;
    }

    protected Example.Criteria buildCriteria(){
        return manager.buildCriteria();
    }

    protected void deleteBefore(Integer t){

    }

    protected void addBefore(T t){

    }

    protected void updateBefore(T t){

    }

}
