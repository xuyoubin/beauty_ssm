package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum PlatformEnum implements ValuedEnum {

    TAO_BAO_PLATFORM(0,"淘宝"),
    TIAN_MAO_PLATFORM(1,"天猫"),
    JING_DONG_PLATFORM(2,"京东"),
    PIN_DUO_DUO_PLATFORM(3,"拼多多"),
    WEI_PIN_HUI_PLATFORM(4,"唯品会");

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

    PlatformEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static PlatformEnum stateOf(int value) {
        for (PlatformEnum p : PlatformEnum.values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        return null;
    }


}
