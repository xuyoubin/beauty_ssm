package com.muma.enums;

import com.muma.enums.base.ValuedEnum;
import org.apache.commons.lang3.StringUtils;

public enum SexEnum implements ValuedEnum {

    WOMAN_SEX(0,"女"),
    MAN_SEX(1,"男");

    private Integer value;
    private String text;

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getText() {
        return text;
    }

    SexEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static SexEnum stateOf(int value) {
        for (SexEnum s : SexEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }

    public static String getValueStr(){
        String valueStr = "";
        for (SexEnum s : SexEnum.values()) {
            valueStr +=s.getValue()+",";
        }
        if(StringUtils.isNotEmpty(valueStr)){
            valueStr = valueStr.substring(0,valueStr.length()-1);
        }
        return valueStr;
    }


}
