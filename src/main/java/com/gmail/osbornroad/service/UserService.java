package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.User;
import com.gmail.osbornroad.repository.jpa.UserRepository;
import com.gmail.osbornroad.util.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    private List<String> getAllUserNames() {
        List<String> allUserNames = new ArrayList<>();
        List<User> userList = findAllUsers();
        for(User user : userList) {
            allUserNames.add(user.getName());
        }
        return allUserNames;
    }

    public User saveUser(User user) throws RegistrationException {
        if (user.getRegistered() == null) {
            user.setRegistered(LocalDateTime.now());
        }
/*        List<User> userList = findAllUsers();
        for(User savedUser : userList) {

        }
        if(getAllUserNames().contains(user.getName())){
            throw new RegistrationException(user.getName());
        }*/

/*        Integer userId = user.getId();
        String rawPassword = user.getPassword();
        if (userId != null) {
            String savedPassword = findUserById(userId).getPassword();
            if (!rawPassword.equals(savedPassword)) {
                user.setPassword(passwordEncoder.encode(rawPassword));
            }
        } else {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }*/

        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
