package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.User;
import com.gmail.osbornroad.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(userList::add);
        return userList;
    }

    @Transactional(readOnly = true)
    public User findUserById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
