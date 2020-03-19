package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum OrderStatusEnum implements ValuedEnum {

    INIT(0,"初始状态"),
    ONE_STEP_OVER(10,"步骤1完成"),
    TWO_STEP_OVER(20,"步骤2完成"),
    COMMIT(30,"提交订单"),
    COMPLAIN(40,"投诉状态"),//确认为投诉单，需要罚款
    CANCEL(50,"取消状态"),
    COMPLAIN_OVER(60,"投诉单买家确认"),
    CONFIRM_PASS(90,"审核通过"),
    CONFIRM_NO_PASS(100,"审核不通过");

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
