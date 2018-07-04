package com.gmail.osbornroad.model.jpa;

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
public class Part implements Serializable {

    private Integer id;
    private String partName;
    private Integer partTypeId;
    private Set<Operation> operationSet = new HashSet<>();

    public Part() {
    }

    public Part(String partName, Integer partTypeId) {
        this.partName = partName;
        this.partTypeId = partTypeId;
    }

    public Part(String partName, Integer partTypeId, Set<Operation> operationSet) {
        this.partName = partName;
        this.partTypeId = partTypeId;
        this.operationSet = operationSet;
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

    @Column(name="part_name")
    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    @Column(name="part_type_id")
    public Integer getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(Integer partTypeId) {
        this.partTypeId = partTypeId;
    }

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
                ", partName='" + partName + '\'' +
                ", partTypeId=" + partTypeId +
                '}';
    }
}
