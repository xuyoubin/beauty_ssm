package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum SortEnum implements ValuedEnum {

    ZH(0,"综合"),
    XY(1,"信用"),
    XL(2,"销量"),
    PRICE_DESC(3,"价格降序"),
    PRICE_ASC(4,"价格升序");

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

    SortEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static SortEnum stateOf(int value) {
        for (SortEnum s : SortEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }


}
