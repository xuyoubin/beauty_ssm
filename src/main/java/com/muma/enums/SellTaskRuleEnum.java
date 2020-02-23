package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum SellTaskRuleEnum implements ValuedEnum {

    COMMODITY_COLLECTION(0,"收藏商品"),
    SHOP_COLLECTION(1,"收藏店铺"),
    CHAT_SHELL(2,"买前咨询聊天"),
    LOOK_TWO_TO_FOUR(3,"浏览2-4家同类店铺商品"),
    LOOK_ONE_TO_THREE(4,"浏览店内1-3款其他商品"),
    LOOK_COMMENT_(5,"浏览评论并点赞1个");

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

    SellTaskRuleEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static SellTaskRuleEnum stateOf(int value) {
        for (SellTaskRuleEnum s : SellTaskRuleEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }


}
