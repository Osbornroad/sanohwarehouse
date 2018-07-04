package com.gmail.osbornroad.model.jpa;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="persons")
public class Person {
    private Integer id;
    private String personName;
//    private Set<Operation> skilledOperations = new HashSet<>();

    public Person() {
    }

    public Person(String personName) {
        this.personName = personName;
    }

/*    public Person(String personName, Set<Operation> skilledOperations) {
        this.personName = personName;
        this.skilledOperations = skilledOperations;
    }*/

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="person_name")
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

/*    public Set<Operation> getSkilledOperations() {
        return skilledOperations;
    }

    public void setSkilledOperations(Set<Operation> skilledOperations) {
        this.skilledOperations = skilledOperations;
    }*/

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                '}';
    }
}
