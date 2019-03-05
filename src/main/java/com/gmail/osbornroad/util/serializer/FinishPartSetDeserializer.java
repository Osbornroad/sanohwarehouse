package com.gmail.osbornroad.util.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gmail.osbornroad.model.jpa.FinishPart;
import com.gmail.osbornroad.model.jpa.PartType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FinishPartSetDeserializer extends StdDeserializer<Set<FinishPart>> {

    public FinishPartSetDeserializer() {
        this(null);
    }

    public FinishPartSetDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Set<FinishPart> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String parsed = jsonParser.getText();
        Set<FinishPart> set = new HashSet<>();
        FinishPart finishPart = new FinishPart(1, "17501-4CM0A", PartType.CLUSTER);
        set.add(finishPart);
        return set;
    }
}
