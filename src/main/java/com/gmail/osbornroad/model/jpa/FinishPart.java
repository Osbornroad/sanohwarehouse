package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="finish_parts")
public class FinishPart extends NamedEntity{

    private PartType partType;
    private Set<Variant> variantSet = new HashSet<>();
    private Set<Incoming> incomingSet = new HashSet<>();

    public FinishPart() {
    }

    public FinishPart(PartType partType, String name, Integer id) {
        this(id, name, partType);
    }

    public FinishPart(String name, PartType partType) {
        super(name);
        this.partType = partType;
    }

    public FinishPart(Integer id, String name, PartType partType) {
        super(id, name);
        this.partType = partType;
    }

    public FinishPart(String name, PartType partType, Set<Variant> variantSet) {
        super(name);
        this.partType = partType;
        this.variantSet = variantSet;
    }

    public FinishPart(Integer id, String name, PartType partType, Set<Variant> variantSet) {
        super(id, name);
        this.partType = partType;
        this.variantSet = variantSet;
    }

    public FinishPart(String name, PartType partType, Set<Variant> variantSet, Set<Incoming> incomingSet) {
        super(name);
        this.partType = partType;
        this.variantSet = variantSet;
        this.incomingSet = incomingSet;
    }

    public FinishPart(Integer id, String name, PartType partType, Set<Variant> variantSet, Set<Incoming> incomingSet) {
        super(id, name);
        this.partType = partType;
        this.variantSet = variantSet;
        this.incomingSet = incomingSet;
    }

    @Column(name="part_type")
    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable (name="variants_finish_parts",
            joinColumns = @JoinColumn(name="finish_parts_id"),
            inverseJoinColumns = @JoinColumn(name="variants_id"))
    public Set<Variant> getVariantSet() {
        return variantSet;
    }

    public void setVariantSet(Set<Variant> variantSet) {
        this.variantSet = variantSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "finishPart")
    public Set<Incoming> getIncomingSet() {
        return incomingSet;
    }

    public void setIncomingSet(Set<Incoming> incomingSet) {
        this.incomingSet = incomingSet;
    }

    @Override
    public String toString() {
        return "FinishPart{" +
                "partType=" + partType +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
