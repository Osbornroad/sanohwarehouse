package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Set<Part> partSet = new HashSet<>();

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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "part_operation_detail",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    public Set<Part> getPartSet() {
        return partSet;
    }

    public void setPartSet(Set<Part> partSet) {
        this.partSet = partSet;
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
