package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum RoalEnum implements ValuedEnum {

    BUYER_ROAL(0,"买家"),
    BUSINESS_ROAL(1,"商家"),
    SYSTEM_ROAL(2,"平台管理员");

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

    RoalEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static RoalEnum stateOf(int value) {
        for (RoalEnum r : RoalEnum.values()) {
            if (r.getValue() == value) {
                return r;
            }
        }
        return null;
    }


}
