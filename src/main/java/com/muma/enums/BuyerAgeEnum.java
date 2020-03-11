package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum BuyerAgeEnum implements ValuedEnum {

    YOUNG(1,"18 ~ 25"),
    BIG_YOUNG(2,"25 ~ 35"),
    MIDDLE(3,"35 ~ 45"),
    OLD(4,"45 ~ 以上");

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

    BuyerAgeEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static BuyerAgeEnum stateOf(int value) {
        for (BuyerAgeEnum b : BuyerAgeEnum.values()) {
            if (b.getValue() == value) {
                return b;
            }
        }
        return null;
    }


}
