package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gmail.osbornroad.util.serializer.FinishPartSetDeserializer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="variants")
public class Variant extends NamedEntity {

    private Project project;
//    @JsonDeserialize(using = FinishPartSetDeserializer.class)
    private Set<FinishPart> finishPartSet = new HashSet<>();

    public Variant() {
    }

    public Variant(String name, Project project) {
        super(name);
        this.project = project;
    }

    public Variant(Integer id, String name, Project project) {
        super(id, name);
        this.project = project;
    }

    public Variant(String name, Project project, Set<FinishPart> finishPartSet) {
        super(name);
        this.project = project;
        this.finishPartSet = finishPartSet;
    }

    public Variant(Integer id, String name, Project project, Set<FinishPart> finishPartSet) {
        super(id, name);
        this.project = project;
        this.finishPartSet = finishPartSet;
    }

    @Column(name="project")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="variants_finish_parts",
            joinColumns = @JoinColumn(name="variants_id"),
            inverseJoinColumns = @JoinColumn(name="finish_parts_id"))
    public Set<FinishPart> getFinishPartSet() {
        return finishPartSet;
    }

    public void setFinishPartSet(Set<FinishPart> finishPartSet) {
        this.finishPartSet = finishPartSet;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "project=" + project +
                ", finishPartSet=" + finishPartSet +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
