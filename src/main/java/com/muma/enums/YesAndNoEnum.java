package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum YesAndNoEnum implements ValuedEnum {

    YES_STATUS(0,"是"),//女
    NO_STATUS(1,"否");//男

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

    YesAndNoEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static YesAndNoEnum stateOf(int value) {
        for (YesAndNoEnum v : YesAndNoEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }


}
