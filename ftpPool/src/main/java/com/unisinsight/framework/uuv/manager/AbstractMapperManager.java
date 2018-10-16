/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.base.ListParams;
import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.common.enums.DeletedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * manager层实现类
 *
 * @author liuran [KF.liuranA@h3c.com]
 * @date 2018/9/7 9:44
 * @since 1.0
 */
public class AbstractMapperManager<T> implements MapperManager<T>{

    @Autowired
    protected Mapper<T> mapper;

    /**
     * modelClass
     */
    private Class<T> modelClass;

    /**
     * id字段名
     */
    private String idFieldName;

    /**
     * 是否包含delete字段
     */
    private boolean haveDeletedField = false;

    private String deletedFieldName = "deleted";

    public AbstractMapperManager() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];

        for (Field field : modelClass.getDeclaredFields()){
            if ("deleted".equals(field.getName())){
                haveDeletedField = true;
            }
            if (field.getAnnotation(Id.class) != null){
                idFieldName = field.getName();
            }
        }
    }

    public String getIdFieldName() {
        return idFieldName;
    }


    @Override
    public List<T> selectAll(T t) {
        return mapper.select(t);
    }

    @Override
    public List<T> selectAll(Example.Criteria criteria) {
        Condition condition = buildCondition();
        condition.and(criteria);
        return selectAll(condition);
    }

    @Override
    public List<T> selectAll(Example.Criteria criteria, String ... filed) {
        Condition condition = buildCondition();
        condition.and(criteria);
        condition.selectProperties(filed);
        return selectAll(condition);
    }


    @Override
    public List<T> selectAll(Condition condition) {
        addDeletedCriteria(condition);
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> selectAll() {
        Condition condition = buildCondition();
        addDeletedCriteria(condition);

        return selectAll(condition);
    }

    @Override
    public T selectOne(Example.Criteria criteria, String... field) {
        Condition condition = buildCondition();
        condition.and(criteria);
        condition.selectProperties(field);
        List<T> result = selectAll(condition);
        if (CollectionUtils.isEmpty(result)){
            return null;
        }

        if (result.size() > 1){
            throw new RuntimeException("查询结果不唯一");
        }

        return result.get(0);
    }

    @Override
    public T selectOne(Example.Criteria criteria) {
        List<T> result = selectAll(criteria);
        if (CollectionUtils.isEmpty(result)){
            return null;
        }

        if (result.size() > 1){
            throw new RuntimeException("查询结果不唯一");
        }

        return result.get(0);
    }

    @Override
    public T selectOne(Condition condition) {
        List<T> result = selectAll(condition);
        if (CollectionUtils.isEmpty(result)){
            return null;
        }

        if (result.size() > 1){
            throw new RuntimeException("查询结果不唯一");
        }

        return result.get(0);
    }

    @Override
    public int count(Condition condition) {
        addDeletedCriteria(condition);
        return mapper.selectCountByCondition(condition);
    }

    @Override
    public int count(Example.Criteria criteria) {
        Condition condition = buildCondition();
        condition.and(criteria);
        return count(condition);
    }

    @Override
    public int count(T t) {
        if (haveDeletedField){
            setFieldValue(t, deletedFieldName, DeletedStatus.DELETED.value());
        }

        return mapper.selectCount(t);
    }

    @Override
    public int insert(T t) {
        if (haveDeletedField){
            setFieldValue(t, deletedFieldName, DeletedStatus.EXIST.value());
        }
        return mapper.insertSelective(t);
    }

    @Override
    public int insert(List<T> list) {
        if (haveDeletedField){
            for (T t : list ){
                setFieldValue(t, deletedFieldName, DeletedStatus.EXIST.value());
            }
        }
        return mapper.insertList(list);
    }

    @Override
    public int update(T t) {
        checkHavePrimaryKey(t);
        Object idValue = getFieldValue(t, idFieldName);
        if (get(idValue) == null){
            throw new RuntimeException("资源不存在");
        }

        return mapper.updateByPrimaryKey(t);
    }

    @Override
    public int updateSelective(T t) {
        checkHavePrimaryKey(t);
        Object idValue = getFieldValue(t, idFieldName);
        if (get(idValue) == null){
            throw new RuntimeException("资源不存在");
        }
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public T get(Object id) {
        Condition condition = buildCondition();
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo(idFieldName, id);

        return selectOne(condition);
    }

    @Override
    public void delete(Integer id) {

        if (get(id) == null){
            throw new RuntimeException("资源不存在");
        }

        if (haveDeletedField){
            mapper.updateByPrimaryKeySelective(createDeletedModel(id));
        } else {
            mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void delete(Set<Integer> ids){
        Example.Criteria criteria = buildCriteria();
        criteria.andIn(idFieldName, ids);
        deleteByCriteria(criteria);
    }

    @Override
    public void delete(T t) {
        if (haveDeletedField){
            Object idValue = getFieldValue(t, idFieldName);
            mapper.updateByPrimaryKeySelective(createDeletedModel(idValue));
        } else {
            mapper.delete(t);
        }
    }

    @Override
    public void deleteByCriteria(Example.Criteria criteria) {
        Condition condition = buildCondition();
        condition.and(criteria);
        if (haveDeletedField){
            try {
                T bean = modelClass.newInstance();
                setFieldValue(bean, deletedFieldName, DeletedStatus.DELETED.value());
                mapper.updateByConditionSelective(bean, condition);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            mapper.deleteByCondition(condition);
        }
    }

    @Override
    public List<T> list(ListParams<T> listParams) {
        return null;
    }

    /**
     * true为存在，false为不存在
     * @param id
     * @return
     */
    @Override
    public boolean isExist(Object id) {
        return get(id) != null;
    }

    @Override
    public Class<T> getModelClass() {
        return modelClass;
    }

    public Example.Criteria buildCriteria(){
        Condition condition = new Condition(modelClass);
        return condition.createCriteria();
    }

    private Condition buildCondition(){
        return new Condition(modelClass);
    }


    private void addDeletedCriteria(Condition condition){
        if (haveDeletedField){
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo(deletedFieldName, DeletedStatus.EXIST.value());
            condition.and(criteria);
        }
    }


    private T createDeletedModel(Object id){
        T bean = null;
        try {
            bean = modelClass.newInstance();
            setFieldValue(bean, idFieldName, id);
            setFieldValue(bean, deletedFieldName, DeletedStatus.DELETED.value());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }

    private void checkHavePrimaryKey(T bean){
       if (getFieldValue(bean, idFieldName) == null){
           throw new RuntimeException("主键不能为空");
       }
    }

    private Object getFieldValue(T bean, String fieldName){
        try {
            Class userCla = bean.getClass();
            Field[] fs = userCla.getDeclaredFields();
            for(int i = 0 ; i < fs.length; i++){
                Field f = fs[i];
                f.setAccessible(true);
                if (f.getName().equals(fieldName)){
                    return f.get(bean);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setFieldValue(T bean, String fieldName, Object value){
        try {
            Class userCla = bean.getClass();
            Field[] fs = userCla.getDeclaredFields();
            for(int i = 0 ; i < fs.length; i++){
                Field f = fs[i];
                f.setAccessible(true);
                if (f.getName().equals(fieldName)){
                    f.set(bean, value);
                    return ;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
