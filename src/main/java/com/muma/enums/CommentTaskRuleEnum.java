package com.muma.enums;

import com.muma.enums.base.ValuedEnum;

public enum CommentTaskRuleEnum implements ValuedEnum {

    COMMENT_FREE(0,"自由发挥"),
    COMMENT_ASSIGN(1,"指定评论"),
    COMMENT_AND_IMAGE(2,"好评+图片"),
    COMMENT_REPEAT(3,"追评"),
    COMMENT_REPEAT_AND_IMAGE(4,"追评+图片");

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

    CommentTaskRuleEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static CommentTaskRuleEnum stateOf(int value) {
        for (CommentTaskRuleEnum c : CommentTaskRuleEnum.values()) {
            if (c.getValue() == value) {
                return c;
            }
        }
        return null;
    }


}
