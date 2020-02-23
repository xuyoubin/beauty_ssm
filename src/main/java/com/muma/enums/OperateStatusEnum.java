package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum OperateStatusEnum implements ValuedEnum {

    TASK_NOT_START(0,"未进行"),
    TASK_PROCESS(1,"进行中"),
    TASK_OVER(2,"结束"),
    TASK_CANCEL(3,"任务撤销");


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

    OperateStatusEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static OperateStatusEnum stateOf(int value) {
        for (OperateStatusEnum o : OperateStatusEnum.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }


}
