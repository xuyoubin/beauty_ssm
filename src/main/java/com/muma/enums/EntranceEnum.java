package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum EntranceEnum implements ValuedEnum {

    PC(0,"电脑"),
    PHONE(1,"手机");

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

    EntranceEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static EntranceEnum stateOf(int value) {
        for (EntranceEnum e : EntranceEnum.values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }


}
