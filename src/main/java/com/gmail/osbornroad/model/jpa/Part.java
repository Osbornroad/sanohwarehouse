package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name="parts")
/*@NamedQueries({
        @NamedQuery(name="Part.findAll", query = "select c from Part c")
})*/
public class Part extends BaseEntity {

    private Integer partTypeId;
    private Set<Operation> operationSet = new HashSet<>();

    public Part() {
    }

    public Part(String name, Integer partTypeId) {
        this.name = name;
        this.partTypeId = partTypeId;
    }

    public Part(String name, Integer partTypeId, Set<Operation> operationSet) {
        this.name = name;
        this.partTypeId = partTypeId;
        this.operationSet = operationSet;
    }

    public Part(Integer id, String name, Integer partTypeId, Set<Operation> operationSet) {
        this.id = id;
        this.name = name;
        this.partTypeId = partTypeId;
        this.operationSet = operationSet;
    }

    @Column(name="part_type_id")
    public Integer getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(Integer partTypeId) {
        this.partTypeId = partTypeId;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "part_operation_detail",
        joinColumns = @JoinColumn(name = "part_id"),
        inverseJoinColumns = @JoinColumn(name = "operation_id"))
    public Set<Operation> getOperationSet() {
        return operationSet;
    }

    public void setOperationSet(Set<Operation> operationSet) {
        this.operationSet = operationSet;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", partTypeId=" + partTypeId +
                '}';
    }
}
