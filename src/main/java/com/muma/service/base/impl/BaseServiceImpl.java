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

import com.muma.common.Sort;
import com.muma.service.base.BaseService;
import com.sun.jndi.toolkit.dir.SearchFilter;
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

    public int countAll() {
        return this.mapper.selectCount((Object)null);
    }

    public int count(T t) {
        return this.mapper.selectCount(t);
    }

    public int count(Map<String, Object> searchParams) {
        Example example = new Example(this.clazz);
        Criteria criteria = example.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        DynamicCriteria.bySearchFilter(filters, criteria);
        int count = this.mapper.selectCountByExample(example);
        return count;
    }

    public int create(T entity) {
        return this.mapper.insertSelective(entity);
    }

    public int createByFull(T entity) {
        return this.mapper.insert(entity);
    }

    public int update(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

    public int updateByFull(T entity) {
        return this.mapper.updateByPrimaryKey(entity);
    }

    public int deleteByPrimaryKey(Object key) {
        return this.mapper.deleteByPrimaryKey(key);
    }

    public int delete(T entity) {
        return entity == null ? 0 : this.mapper.delete(entity);
    }

    public int updateByPrimaryKey(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

    public int save(T entity) {
        return this.mapper.insertSelective(entity);
    }

    public PageInfo<T> selectPaged(com.hk.base.dto.request.PageRequest page, Map<String, Object> params) {
        Example example = new Example(this.clazz);
        Criteria criteria = example.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        DynamicCriteria.bySearchFilter(filters, criteria);
        PageHelper.startPage(page.getPage().intValue(), page.getRows().intValue());
        if (StringUtils.isNotEmpty(page.getSort())) {
            page.setSort(StringUtil.camelhumpToUnderline(page.getSort()));
        }

        if (StringUtils.isNotEmpty(page.getSort()) && StringUtils.isNotEmpty(page.getOrder())) {
            String sort = page.getSort() + " " + page.getOrder();
            PageHelper.orderBy(sort);
        }

        List<T> list = this.mapper.selectByExample(example);
        PageInfo<T> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<T> query() {
        return this.mapper.selectAll();
    }

    public List<T> query(Map<String, Object> params) {
        Example example = new Example(this.clazz);
        Criteria criteria = example.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        DynamicCriteria.bySearchFilter(filters, criteria);
        List<T> list = this.mapper.selectByExample(example);
        return list;
    }

    public List<T> query(Map<String, Object> params, String orderByClause) {
        Example example = new Example(this.clazz);
        if (StringUtils.isNotBlank(orderByClause)) {
            example.setOrderByClause(orderByClause);
        }

        Criteria criteria = example.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        DynamicCriteria.bySearchFilter(filters, criteria);
        List<T> list = this.mapper.selectByExample(example);
        return list;
    }
}
