package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum StatusEnum implements ValuedEnum {
    CONFIRM_INIT(0,"初始状态"),
    CONFIRM_WAIT(1,"待审核"),
    CONFIRM_PASS(2,"审核通过"),
    CONFIRM_NO_PASS(3,"审核不通过"),
    USER_BLACK(4,"用户拉黑");//该系统拉黑，防止该用户再注册

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

    StatusEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static StatusEnum stateOf(int value) {
        for (StatusEnum s : StatusEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }


}
