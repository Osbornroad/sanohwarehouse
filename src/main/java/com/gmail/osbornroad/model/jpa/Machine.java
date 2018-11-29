package com.gmail.osbornroad.model.jpa;

import java.util.*;

public enum Machine {
    HPC(1),
    LASER(1),
    CHAMFERING(1),
    BRUSHING(1),
    ENDFORMING_LONG(1),
    ENDFORMING_SHORT(3),
    SUMI_CUTTING(1),
    SUMI_OVEN(1);

    private final Integer quantity;

    Machine(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static Comparator<Machine> machineComparator = Comparator.comparing(obj -> obj.ordinal());

    public static List<Machine> getMachineList() {
        List<Machine> allMachineList = new ArrayList<>();
        allMachineList.addAll(Arrays.asList(Machine.values()));
        Collections.sort(allMachineList, machineComparator);
        return allMachineList;
    }

}
