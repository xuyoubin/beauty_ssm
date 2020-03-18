package com.muma.enums;

import com.muma.enums.base.ValuedEnum;
import org.apache.commons.lang3.StringUtils;

public enum BuyerAgeEnum implements ValuedEnum {

    CHILD(0,"0 ~ 18"),
    YOUNG(1,"18 ~ 25"),
    BIG_YOUNG(2,"25 ~ 35"),
    MIDDLE(3,"35 ~ 45"),
    OLD(4,"45 ~ 以上");

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

    BuyerAgeEnum(Integer value, String text){
        this.value = value;
        this.text =text;
    }

    public static BuyerAgeEnum stateOf(int value) {
        for (BuyerAgeEnum b : BuyerAgeEnum.values()) {
            if (b.getValue() == value) {
                return b;
            }
        }
        return null;
    }

    public static BuyerAgeEnum stateOfAge(Integer age){
        if(age > 0 && age <18){
            return CHILD;
        }else if(age >= 18 && age < 25){
            return  YOUNG;
        }else if(age >= 25 && age < 35){
            return  BIG_YOUNG;
        }else if(age >=35 && age < 45){
            return MIDDLE;
        }else if(age >=45){
            return OLD;
        }else {
            return null;
        }
    }
    public static String getValueStr(){
        String valueStr = "";
        for (BuyerAgeEnum b : BuyerAgeEnum.values()) {
            valueStr +=b.getValue()+",";
        }
        if(StringUtils.isNotEmpty(valueStr)){
            valueStr = valueStr.substring(0,valueStr.length()-1);
        }
        return valueStr;
    }

    public static void main(String[] args) throws Exception {
        System.err.println(getValueStr());
    }


}
