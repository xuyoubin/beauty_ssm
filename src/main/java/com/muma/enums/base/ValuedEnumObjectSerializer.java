//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.enums.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class ValuedEnumObjectSerializer extends JsonSerializer<ValuedEnum> {
    public ValuedEnumObjectSerializer() {
    }

    public void serialize(ValuedEnum valuedEnum, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Object value = valuedEnum.getValue();
        String text = valuedEnum.getText();
        jgen.writeStartObject();
        if (value instanceof Integer) {
            jgen.writeNumberField("value", ((Integer)value).intValue());
        } else {
            jgen.writeStringField("value", value.toString());
        }

        jgen.writeStringField("text", text);
        jgen.writeEndObject();
    }
}
