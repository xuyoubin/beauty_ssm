//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.common;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

public class SearchFilter {
    public String fieldName;
    public Object value;
    public SearchFilter.Operator operator;

    public SearchFilter(String fieldName, SearchFilter.Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = Maps.newHashMap();
        if (searchParams == null) {
            return filters;
        } else {
            Iterator var2 = searchParams.entrySet().iterator();

            while(true) {
                String key;
                Object value;
                while(true) {
                    do {
                        if (!var2.hasNext()) {
                            return filters;
                        }

                        Entry<String, Object> entry = (Entry)var2.next();
                        key = (String)entry.getKey();
                        value = entry.getValue();
                    } while(value instanceof String && StringUtils.isBlank((String)value));

                    if (value instanceof Collection) {
                        if (((Collection)value).size() <= 0) {
                            continue;
                        }
                    } else if (value == null) {
                        continue;
                    }
                    break;
                }

                String[] names = StringUtils.split(key, "_");
                if (names.length != 2) {
                    throw new IllegalArgumentException(key + " is not a valid search filter name");
                }

                String filedName = names[1];
                SearchFilter.Operator operator = SearchFilter.Operator.valueOf(names[0]);
                SearchFilter filter = new SearchFilter(filedName, operator, value);
                filters.put(key, filter);
            }
        }
    }

    public static enum Operator {
        EQ,
        NOTEQ,
        LIKE,
        LLIKE,
        RLIKE,
        NOTLIKE,
        GT,
        LT,
        GTE,
        LTE,
        IN,
        ISNULL,
        ISNOTNULL,
        NOTIN;

        private Operator() {
        }
    }
}
