package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum PayTypeEnum implements ValuedEnum {

    FREE_INCOME(0,"佣金收入"),
    COMMENT_FREE_INCOME(1,"评价佣金收入"),

    CASH_OUT(2,"提现支出"),
    PUNISH_OUT(3,"惩罚支出"),

    RECHARGE_INCOME(20,"商家充值"),

    PRICE_OUT(21,"本金支出"),
    FREE_OUT(22,"佣金支出"),
    COMMENT_FREE_OUT(23,"评价佣金支出"),

    REFUND_INCOME(80,"退款"),
    FREEZE_OUT(90,"冻结资金"),
    INVITE_INCOME(100,"邀请收入")




    ;

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

    PayTypeEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static PayTypeEnum stateOf(int value) {
        for (PayTypeEnum p : PayTypeEnum.values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        return null;
    }


}
