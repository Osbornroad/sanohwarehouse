package com.gmail.osbornroad.model;

import javax.persistence.Entity;

@Entity
public class Delivery {

    private Integer field_key;
    private String aPoint;
    private String number;

    public Delivery(Integer field_key, String aPoint, String number) {
        this.field_key = field_key;
        this.aPoint = aPoint;
        this.number = number;
    }

    public Integer getField_key() {
        return field_key;
    }

    public void setField_key(Integer field_key) {
        this.field_key = field_key;
    }

    public String getaPoint() {
        return aPoint;
    }

    public void setaPoint(String aPoint) {
        this.aPoint = aPoint;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "field_key=" + field_key +
                ", aPoint='" + aPoint + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
