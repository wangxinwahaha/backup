/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.base.ListParams;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

/**
 * manager层父接口
 *
 * @author liuran [KF.liuranA@h3c.com]
 * @date 2018/9/7 9:46
 * @since 1.0
 */
public interface MapperManager<T> {
    /**
     * 获取列表
     * @param t
     * @return
     */
    List<T> selectAll(T t);

    /**
     * 获取列表
     * @param criteria
     * @return
     */
    List<T> selectAll(Example.Criteria criteria);

    /**
     * 获取列表
     * @param criteria
     * @param filed
     * @return
     */
    List<T> selectAll(Example.Criteria criteria, String... filed);

    /**
     * 获取列表
     * @param condition
     * @return
     */
    List<T> selectAll(Condition condition);

    /**
     * 获取列表
     * @return
     */
    List<T> selectAll();

    /**
     * 获取单个对象
     * @param criteria
     * @return
     */
    T selectOne(Example.Criteria criteria);

    /**
     * 获取单个对象
     * @param criteria
     * @param field
     * @return
     */
    T selectOne(Example.Criteria criteria, String... field);

    /**
     * 获取单个对象
     * @param condition
     * @return
     */
    T selectOne(Condition condition);

    /**
     * 统计条数
     * @param condition
     * @return
     */
    int count(Condition condition);

    /**
     * 统计条数
     * @param criteria
     * @return
     */
    int count(Example.Criteria criteria);

    /**
     * 统计条数
     * @param t
     * @return
     */
    int count(T t);

    /**
     * 插入数据，若属性值为null，则使用数据库默认值
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 批量插入数据，若属性值为null，则使用数据库默认值
     * @param t
     * @return
     */
    int insert(List<T> t);

    /**
     * 更新数据
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 更新数据，若属性值为null，则使用数据库默认值
     * @param t
     * @return
     */
    int updateSelective(T t);

    /**
     * 获取单个对象
     * @param id
     * @return
     */
    T get(Object id);

    /**
     * 通过id删除数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 删除数据
     * @param t
     */
    void delete(T t);

    /**
     * 批量删除数据
     * @param ids
     */
    void delete(Set<Integer> ids);

    /**
     * 删除数据
     * @param criteria
     */
    void deleteByCriteria(Example.Criteria criteria);

    /**
     * 获取列表，可分页
     * @param listParams
     * @return
     */
    List<T> list(ListParams<T> listParams);

    /**
     * 判断资源是否存在
     * @param id
     * @return
     */
    boolean isExist(Object id);

    /**
     * 获取泛型参数类型
     * @return
     */
    Class<T> getModelClass();

}
