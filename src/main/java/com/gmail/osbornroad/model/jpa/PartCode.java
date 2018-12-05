package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.osbornroad.util.serializer.PartCodeSerializer;

import java.util.*;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonSerialize(using = PartCodeSerializer.class)
public enum PartCode {
    T3080PC0("3080PC0"),
    T1063ACT("1063ACT"),
    T10639C0("10639C0"),
    T1063PC0("1063PC0"),
    T2047NEO("2047NEO"),
    T2063NEO("2063NEO"),
    T3100PC0("3100PC0"),
    T20638ET("20638ET"),
    T20478ET("20478ET");

    private final String code;

    PartCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

//    @JsonValue
    public String getName() {
        return name();
    }

//    public static Comparator<PartCode> partCodeComparator = Comparator.comparing(Enum::ordinal);

    public static Comparator<PartCode> partCodeComparator = Comparator.comparing(o -> o.code);

    public static List<PartCode> getPartCodeList() {
        List<PartCode> partCodeList = new ArrayList<>(Arrays.asList(PartCode.values()));
        partCodeList.sort(partCodeComparator);
        return partCodeList;
    }
}
