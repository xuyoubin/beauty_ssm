//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import tk.mybatis.mapper.entity.Example.Criteria;

public class DynamicCriteria {
    public DynamicCriteria() {
    }

    public static void bySearchFilter(Map<String, SearchFilter> filters, Criteria criteria) {
        if (filters != null && !filters.isEmpty()) {
            Iterator var2 = filters.entrySet().iterator();

            while(var2.hasNext()) {
                Entry<String, SearchFilter> entry = (Entry)var2.next();
                SearchFilter filter = (SearchFilter)entry.getValue();
                Object[] os;
                switch(filter.operator) {
                    case EQ:
                        criteria.andEqualTo(filter.fieldName, filter.value);
                        break;
                    case NOTEQ:
                        criteria.andNotEqualTo(filter.fieldName, filter.value);
                        break;
                    case LIKE:
                        criteria.andLike(filter.fieldName, "%" + filter.value.toString() + "%");
                        break;
                    case LLIKE:
                        criteria.andLike(filter.fieldName, "%" + filter.value.toString());
                        break;
                    case RLIKE:
                        criteria.andLike(filter.fieldName, filter.value.toString() + "%");
                        break;
                    case NOTLIKE:
                        criteria.andNotLike(filter.fieldName, filter.value.toString());
                        break;
                    case GT:
                        criteria.andGreaterThan(filter.fieldName, filter.value);
                        break;
                    case LT:
                        criteria.andLessThan(filter.fieldName, filter.value);
                        break;
                    case GTE:
                        criteria.andGreaterThanOrEqualTo(filter.fieldName, filter.value);
                        break;
                    case LTE:
                        criteria.andLessThanOrEqualTo(filter.fieldName, filter.value);
                        break;
                    case ISNULL:
                        criteria.andIsNull(filter.fieldName);
                        break;
                    case ISNOTNULL:
                        criteria.andIsNotNull(filter.fieldName);
                        break;
                    case NOTIN:
                        if (filter.value instanceof List) {
                            criteria.andNotIn(filter.fieldName, (List)filter.value);
                        } else if (filter.value.getClass().isArray()) {
                            os = (Object[])((Object[])filter.value);
                            criteria.andNotIn(filter.fieldName, Arrays.asList(os));
                        }
                        break;
                    case IN:
                        if (filter.value instanceof List) {
                            criteria.andIn(filter.fieldName, (List)filter.value);
                        } else if (filter.value.getClass().isArray()) {
                            os = (Object[])((Object[])filter.value);
                            criteria.andIn(filter.fieldName, Arrays.asList(os));
                        }
                }
            }
        }

    }
}
