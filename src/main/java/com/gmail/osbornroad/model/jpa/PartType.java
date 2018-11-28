package com.gmail.osbornroad.model.jpa;

import java.util.*;

public enum PartType {

    TUBE,
    CLIP,
    BRACKET,
    NUT,
    CLUSTER,
    ABS_CLUSTER;

    public static Comparator<PartType> partTypeComparator = Comparator.comparing(obj -> obj.ordinal());

    public static List<PartType> getPartTypeList() {
        List<PartType> partTypeList = new ArrayList<>();
        partTypeList.addAll(Arrays.asList(PartType.values()));
        Collections.sort(partTypeList, partTypeComparator);
        return partTypeList;
    }
}
