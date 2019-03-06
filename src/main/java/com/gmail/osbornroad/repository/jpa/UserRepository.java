package com.gmail.osbornroad.repository.jpa;

import com.gmail.osbornroad.model.jpa.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
