package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum OrderStatusEnum implements ValuedEnum {

    INIT(0,"初始状态"),
    ONE_STEP_OVER(10,"步骤1完成"),
    TWO_STEP_OVER(20,"步骤2完成"),
    COMMIT(30,"提交订单"),
    BUSINESS_COMPLAIN(40,"商家投诉"),
    CANCEL(50,"取消状态"),
    BUYER_COMPLAIN_SURE(60,"买家确认投诉单"),
    BUSINESS_PASS(90,"商家审核通过"),
    PLATFORM_CONFIRM_PASS(100,"平台审核投诉单通过"),
    PLATFORM_CONFIRM_NO_PASS(110,"平台审核投诉单驳回");

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

    OrderStatusEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static OrderStatusEnum stateOf(int value) {
        for (OrderStatusEnum o : OrderStatusEnum.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }


}
