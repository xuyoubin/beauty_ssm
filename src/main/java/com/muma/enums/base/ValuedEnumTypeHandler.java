//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.enums.base;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class ValuedEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private Class<E> type;
    private Map<Object, E> map = new HashMap();

    public ValuedEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.type = type;
            E[] enums = (E[]) type.getEnumConstants();
            if (enums == null) {
                throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
            } else {
                Enum[] var3 = enums;
                int var4 = enums.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    E e = (E) var3[var5];
                    ValuedEnum valuedEnum = (ValuedEnum)e;
                    this.map.put(valuedEnum.getValue(), e);
                }

            }
        }
    }

    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ValuedEnum valuedEnum = (ValuedEnum)parameter;
        Object value = valuedEnum.getValue();
        if (value instanceof Integer) {
            ps.setInt(i, ((Integer)value).intValue());
        } else {
            ps.setString(i, value.toString());
        }

    }

    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object value = rs.getObject(columnName);
        return rs.wasNull() ? null : this.getValuedEnum(value);
    }

    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object value = rs.getObject(columnIndex);
        return rs.wasNull() ? null : this.getValuedEnum(value);
    }

    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object value = cs.getObject(columnIndex);
        return cs.wasNull() ? null : this.getValuedEnum(value);
    }

    private E getValuedEnum(Object value) {
        try {
            return (E) this.map.get(value);
        } catch (Exception var3) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + this.type.getSimpleName() + " by value.", var3);
        }
    }
}
