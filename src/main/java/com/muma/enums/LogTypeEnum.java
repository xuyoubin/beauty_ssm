package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum LogTypeEnum implements ValuedEnum {

    QUERY_INFO(0,"查询操作"),
    ADD_INFO(1,"添加信息"),
    CASH_OUT(2,"提现操作"),
    MONEY_INPUT(3,"充值操作"),
    REFUND_MONEY(4,"退款操作"),
    RELEASE_TASK(5,"发布任务"),
    TASK_COMMIT(6,"任务提交"),
    GET_TASK(7,"获得任务"),
    BUYER_CANCEL_TASK(8,"买家取消任务"),
    BUSINESS_CANCEL_TASK(9,"商家取消任务");


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

    LogTypeEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static LogTypeEnum stateOf(int value) {
        for (LogTypeEnum l : LogTypeEnum.values()) {
            if (l.getValue() == value) {
                return l;
            }
        }
        return null;
    }


}
