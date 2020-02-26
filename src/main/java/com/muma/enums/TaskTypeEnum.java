package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum TaskTypeEnum implements ValuedEnum {

    SELLE_TASK(0,"销售任务"),
    COMMENT_TASK(1,"评价任务"),
    LOOK_TASK(2,"浏览任务"),
    LOOK_AND_SHOP(3,"当天浏览第二天购买任务"),
    REPEAT_SHOP(4,"复购任务");

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

    TaskTypeEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static TaskTypeEnum stateOf(int value) {
        for (TaskTypeEnum t : TaskTypeEnum.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null;
    }


}
