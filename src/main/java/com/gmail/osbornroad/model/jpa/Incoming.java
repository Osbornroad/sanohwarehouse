package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gmail.osbornroad.util.DateTimeUtil;
import com.gmail.osbornroad.util.serializer.FinishPartSetDeserializer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="incomings")
public class Incoming extends BaseEntity {

    private FinishPart finishPart;

    private Integer quantity;

    private LocalDateTime incomingDateTime;

    private String comments;

    public Incoming() {
    }

    public Incoming(FinishPart finishPart, Integer quantity, LocalDateTime incomingDateTime, String comments) {
        this.finishPart = finishPart;
        this.quantity = quantity;
        this.incomingDateTime = incomingDateTime;
        this.comments = comments;
    }

    public Incoming(Integer id, FinishPart finishPart, Integer quantity, LocalDateTime incomingDateTime, String comments) {
        super(id);
        this.finishPart = finishPart;
        this.quantity = quantity;
        this.incomingDateTime = incomingDateTime;
        this.comments = comments;
    }

    public Incoming(FinishPart finishPart, Integer quantity, LocalDateTime incomingDateTime) {
        this.finishPart = finishPart;
        this.quantity = quantity;
        this.incomingDateTime = incomingDateTime;
    }

    public Incoming(Integer id, FinishPart finishPart, Integer quantity, LocalDateTime incomingDateTime) {
        super(id);
        this.finishPart = finishPart;
        this.quantity = quantity;
        this.incomingDateTime = incomingDateTime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "finish_part_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public FinishPart getFinishPart() {
        return finishPart;
    }

    public void setFinishPart(FinishPart finishPart) {
        this.finishPart = finishPart;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "incoming_date_time")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    public LocalDateTime getIncomingDateTime() {
        return incomingDateTime;
    }

    public void setIncomingDateTime(LocalDateTime incomingDateTime) {
        this.incomingDateTime = incomingDateTime;
    }

    @Override
    public String toString() {
        return "Incoming{" +
                "finishPart=" + finishPart +
                ", quantity=" + quantity +
                ", incomingDateTime=" + incomingDateTime +
                ", comments='" + comments + '\'' +
                ", id=" + id +
                '}';
    }
}
