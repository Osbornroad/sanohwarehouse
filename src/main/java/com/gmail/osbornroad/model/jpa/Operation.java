package com.gmail.osbornroad.model.jpa;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="operations")
public class Operation implements Serializable {

    private Integer id;
    private String operationName;
    private Integer operationSequence;

    public Operation() {
    }

    public Operation(String operationName, Integer operationSequence) {
        this.operationName = operationName;
        this.operationSequence = operationSequence;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="operation_name")
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Column(name="operation_sequence")
    public Integer getOperationSequence() {
        return operationSequence;
    }

    public void setOperationSequence(Integer operationSequence) {
        this.operationSequence = operationSequence;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", operationName='" + operationName + '\'' +
                ", operationSequence=" + operationSequence +
                '}';
    }
}
