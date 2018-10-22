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

    private PartType partType;
    private Set<Operation> operationSet = new HashSet<>();

    public Part() {
    }

    public Part(String name, PartType partType) {
        super(name);
        this.partType = partType;
    }

    public Part(String name, PartType partType, Set<Operation> operationSet) {
        super(name);
        this.partType = partType;
        this.operationSet = operationSet;
    }

    public Part(Integer id, String name, PartType partType, Set<Operation> operationSet) {
        super(id, name);
        this.partType = partType;
        this.operationSet = operationSet;
    }

    @Column(name="part_type")
    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

//    @JsonIgnore
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        if (id != null ? !id.equals(part.id) : part.id != null) return false;
        if (name != null ? !name.equals(part.name) : part.name != null) return false;
        return partType == part.partType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (partType != null ? partType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", partType=" + partType +
                '}';
    }
}
