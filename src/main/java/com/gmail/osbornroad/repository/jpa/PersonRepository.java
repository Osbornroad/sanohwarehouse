package com.gmail.osbornroad.repository.jpa;

import com.gmail.osbornroad.model.jpa.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
