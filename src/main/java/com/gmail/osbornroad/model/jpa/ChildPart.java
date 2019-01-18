package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "child_parts")
public class ChildPart extends BaseEntity{

    private Part part;

    private Integer childPartId;

    private Integer quantity;

    public ChildPart() {
    }

    public ChildPart(Integer id, Part part, Integer childPartId, Integer quantity) {
        super(id);
        this.part = part;
        this.childPartId = childPartId;
        this.quantity = quantity;
    }

    public ChildPart(Part part, Integer childPartId, Integer quantity) {
        this.part = part;
        this.childPartId = childPartId;
        this.quantity = quantity;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @Column(name = "child_part_id")
    public Integer getChildPartId() {
        return childPartId;
    }

    public void setChildPartId(Integer childPartId) {
        this.childPartId = childPartId;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ChildPart{" +
                ", id=" + id +
                "part=" + part +
                ", childPartId=" + childPartId +
                ", quantity=" + quantity +
                '}';
    }
}
