package com.gmail.osbornroad.model;

import java.time.LocalDateTime;

public class FinishPart {

    private Integer id;
    private String partNumber;
//    private Integer recievingId;
    private Integer quantity;
    private LocalDateTime recievingDateTime;
//    private String comment;

    public FinishPart(Integer id, String partNumber, Integer quantity, LocalDateTime recievingDateTime) {
        this.id = id;
        this.partNumber = partNumber;
        this.quantity = quantity;
        this.recievingDateTime = recievingDateTime;
    }

    public FinishPart(String partNumber, Integer quantity) {
        this.partNumber = partNumber;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getRecievingDateTime() {
        return recievingDateTime;
    }

    @Override
    public String toString() {
        return "FinishPart{" +
                "id=" + id +
                ", partNumber='" + partNumber + '\'' +
                ", quantity=" + quantity +
                ", recievingDateTime=" + recievingDateTime +
                '}';
    }
}
