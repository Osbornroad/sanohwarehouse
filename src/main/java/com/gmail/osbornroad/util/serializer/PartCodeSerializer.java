package com.gmail.osbornroad.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gmail.osbornroad.model.jpa.PartCode;

import java.io.IOException;

public class PartCodeSerializer extends StdSerializer {

    public PartCodeSerializer() {
        super(PartCode.class);
    }

    public PartCodeSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        PartCode partCode = (PartCode)o;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("name");
        jsonGenerator.writeString(partCode.name());
        jsonGenerator.writeFieldName("code");
        jsonGenerator.writeString(partCode.getCode());
        jsonGenerator.writeEndObject();
    }
}

