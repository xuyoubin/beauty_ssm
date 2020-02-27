//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.service.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muma.common.*;
import com.muma.service.base.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;


public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    protected Mapper<T> mapper;
    protected Class<T> clazz = (Class)((ParameterizedType)((ParameterizedType)this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];

    public BaseServiceImpl() {
    }
    @Override
    public T getEntity(Object key) {
        return this.mapper.selectByPrimaryKey(key);
    }

    @Override
    public List<T> findAll() {
        return this.mapper.selectAll();
    }

    @Override
    public List<T> findList(Map<String, Object> searchParams) {
        return this.findList(searchParams, (Sort)null);
    }

    @Override
    public List<T> findList(Map<String, Object> searchParams, Sort sort) {
        Example example = new Example(this.clazz);
        Example.Criteria criteria = example.createCriteria();
        Map filters = SearchFilter.parse(searchParams);
        DynamicCriteria.bySearchFilter(filters, criteria);
        if (sort != null) {
            example.setOrderByClause(sort.toString());
        }

        List list = this.mapper.selectByExample(example);
        return list;
    }

    @Override
    public Page<T> findPage(PageRequest pageRequest, Map<String, Object> searchParams) {
        Example example = new Example(this.clazz);
        Criteria criteria = example.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        DynamicCriteria.bySearchFilter(filters, criteria);
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize(), pageRequest.isCountTotal());
        String orderBy = pageRequest.getSort() == null ? "" : pageRequest.getSort().toString();
        PageHelper.orderBy(orderBy);
        List<T> list = this.mapper.selectByExample(example);
        PageInfo<T> pageInfo = new PageInfo(list);
        Page<T> page = new Page(list, pageRequest, pageInfo.getTotal());
        return page;
    }

    @Override
    public int count(Map<String, Object> searchParams) {
        Example example = new Example(this.clazz);
        Criteria criteria = example.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        DynamicCriteria.bySearchFilter(filters, criteria);
        int count = this.mapper.selectCountByExample(example);
        return count;
    }

    @Override
    public int create(T entity) {
        return this.mapper.insertSelective(entity);
    }

    @Override
    public int createByFull(T entity) {
        return this.mapper.insert(entity);
    }

    @Override
    public int update(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByFull(T entity) {
        return this.mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return this.mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int delete(T entity) {
        return entity == null ? 0 : this.mapper.delete(entity);
    }

    @Override
    public int updateByPrimaryKey(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

}
