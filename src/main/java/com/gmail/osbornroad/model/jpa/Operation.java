package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Operation {

    HPC("HPC", "HPC"),
    LASER("LASER", "LSR"),
    CHAMFERING("CHAMFERING", "CMF"),
    BRUSHING("BRUSHING", "BSH"),
    REDUCTION("REDUCTION", "RED"),
    ENDFORMING("ENDFORMING", "ENF"),
    SUMI_CUTTING("SUMI_CUTTING", "CUT"),
    SUMI_OVEN("SUMI_OVEN", "OVE"),
    BENDING("BENDING", "BND"),
    ASSEMBLY("ASSEMBLY", "ASY"),
    DELIVERY("DELIVERY", "DEL");

    private final String fullName;
    private final String shortName;

    Operation(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getName() {
        return name();
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Comparator<Operation> operationComparator = Comparator.comparing(obj -> obj.ordinal());

    public static List<Operation> getOperationList() {
        List<Operation> allOperationList = new ArrayList<>();
        allOperationList.addAll(Arrays.asList(Operation.values()));
        Collections.sort(allOperationList, operationComparator);
        return allOperationList;
    }

/*    public static String getOperationsArray() {
        List<Operation> allOperationList = getOperationList();
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0; i < allOperationList.size(); i++){
            sb.append("\"").append(allOperationList.get(i).toString()).append("\"");
            if(i + 1 < allOperationList.size()){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }*/
}
