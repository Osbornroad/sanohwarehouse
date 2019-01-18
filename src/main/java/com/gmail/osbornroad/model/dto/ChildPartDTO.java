package com.gmail.osbornroad.model.dto;

import com.gmail.osbornroad.model.jpa.BaseEntity;
import com.gmail.osbornroad.model.jpa.Part;

import javax.persistence.Entity;

@Entity
public class ChildPartDTO extends BaseEntity {

    private Part part;

    private Integer childPartId;

    private String childPartName;

    private Integer quantity;

    public ChildPartDTO() {
    }

    public ChildPartDTO(Part part, Integer childPartId, String childPartName, Integer quantity) {
        this.part = part;
        this.childPartId = childPartId;
        this.childPartName = childPartName;
        this.quantity = quantity;
    }

    public ChildPartDTO(Integer id, Part part, Integer childPartId, String childPartName, Integer quantity) {
        super(id);
        this.part = part;
        this.childPartId = childPartId;
        this.childPartName = childPartName;
        this.quantity = quantity;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Integer getChildPartId() {
        return childPartId;
    }

    public void setChildPartId(Integer childPartId) {
        this.childPartId = childPartId;
    }

    public String getChildPartName() {
        return childPartName;
    }

    public void setChildPartName(String childPartName) {
        this.childPartName = childPartName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ChildPartDTO{" +
                "id=" + id +
                ", part=" + part +
                ", childPartId=" + childPartId +
                ", childPartName='" + childPartName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
