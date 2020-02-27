package com.muma.service.base;

import com.muma.common.Page;
import com.muma.common.PageRequest;
import com.muma.common.Sort;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    T getEntity(Object var1);
    List<T> findAll();
    List<T> findList(Map<String, Object> var1);
    List<T> findList(Map<String, Object> var1, Sort var2);

    Page<T> findPage(PageRequest var1, Map<String, Object> var2);

    int count(Map<String, Object> var1);

    int create(T var1);

    int createByFull(T var1);

    int update(T var1);

    int updateByFull(T var1);

    int deleteByPrimaryKey(Object var1);

    int delete(T var1);

    int updateByPrimaryKey(T var1);

}
