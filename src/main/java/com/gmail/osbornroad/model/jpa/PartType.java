package com.gmail.osbornroad.model.jpa;

import java.util.*;

public enum PartType {

    TUBE,
    CLIP,
    BRACKET,
    NUT,
    CLUSTER,
    ABS_CLUSTER,
    SINGLE_BRAKE,
    VACUUM;

    public static Comparator<PartType> partTypeComparator = Comparator.comparing(obj -> obj.ordinal());

    public static List<PartType> getPartTypeList() {
        List<PartType> partTypeList = new ArrayList<>(Arrays.asList(PartType.values()));
        partTypeList.sort(partTypeComparator);
        return partTypeList;
    }
}
