package com.muma.service.base;

import com.muma.common.Sort;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    T getEntity(Object var1);
    List<T> findAll();
    List<T> findList(Map<String, Object> var1);
    List<T> findList(Map<String, Object> var1, Sort var2);

}
