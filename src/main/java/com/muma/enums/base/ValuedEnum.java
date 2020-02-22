//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.enums.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(
        using = ValuedEnumObjectSerializer.class
)
public interface ValuedEnum {
    Object getValue();

    String getText();
}
