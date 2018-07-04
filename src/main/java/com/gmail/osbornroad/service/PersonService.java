package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Person;
import com.gmail.osbornroad.repository.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<Person> findAllPersons() {
        List<Person> personList = new ArrayList<>();
        Iterable<Person> iterable = personRepository.findAll();
        iterable.forEach(personList::add);
        return personList;
    }

    @Transactional(readOnly = true)
    public Person findPersonById(Integer id) {
        Optional<Person> optional = personRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }
}
