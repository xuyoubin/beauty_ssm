package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum BuyerRuleEnum implements ValuedEnum {

    REGISTER_BIG_ONE_YEAR(0,"注册满一年"),
    HEART_BIG_THREE(1,"大于3新"),
    HOT_VALUE(2,"淘气值大于400"),
    AGE_VALUE(3,"年龄"),
    SEX_VALUE(4,"性别"),
    AREA_VALUE(5,"区域");


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

    BuyerRuleEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static BuyerRuleEnum stateOf(int value) {
        for (BuyerRuleEnum b : BuyerRuleEnum.values()) {
            if (b.getValue() == value) {
                return b;
            }
        }
        return null;
    }


}
