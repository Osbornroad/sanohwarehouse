package com.gmail.osbornroad.model;

import java.time.LocalDateTime;

public class Recieving {

    private Integer id;
    private Integer recievingId;
    private LocalDateTime recievingDateTime;
    private String comment;

    public Recieving(Integer id, Integer recievingId, LocalDateTime recievingDateTime, String comment) {
        this.id = id;
        this.recievingId = recievingId;
        this.recievingDateTime = recievingDateTime;
        this.comment = comment;
    }

    public Recieving(Integer recievingId, LocalDateTime recievingDateTime, String comment) {
        this.id = null;
        this.recievingId = recievingId;
        this.recievingDateTime = recievingDateTime;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRecievingId() {
        return recievingId;
    }

    public LocalDateTime getRecievingDateTime() {
        return recievingDateTime;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Recieving{" +
                "id=" + id +
                ", recievingId=" + recievingId +
                ", recievingDateTime=" + recievingDateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
