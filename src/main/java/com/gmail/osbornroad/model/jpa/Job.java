package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class Job extends BaseEntity{


    private Part part;

    private Operation operation;

    private Machine machine;

    private Integer cycleTime;

    public Job() {
    }

    public Job(Integer id, Part part, Operation operation, Machine machine, Integer cycleTime) {
        super(id);
        this.part = part;
        this.operation = operation;
        this.machine = machine;
        this.cycleTime = cycleTime;
    }

    public Job(Part part, Operation operation, Machine machine, Integer cycleTime) {
        this.part = part;
        this.operation = operation;
        this.machine = machine;
        this.cycleTime = cycleTime;
    }

    public Job(Operation operation, Machine machine, Integer cycleTime) {
        this.operation = operation;
        this.machine = machine;
        this.cycleTime = cycleTime;
    }

    public Job(Integer id, Operation operation, Machine machine, Integer cycleTime) {
        super(id);
        this.operation = operation;
        this.machine = machine;
        this.cycleTime = cycleTime;
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

    @Column(name = "operation")
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Column(name = "machine")
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Column(name = "cycle_time")
    public Integer getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Integer cycleTime) {
        this.cycleTime = cycleTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", part=" + part +
                ", operation=" + operation +
                ", machine=" + machine +
                ", cycleTime=" + cycleTime +
                '}';
    }
}
