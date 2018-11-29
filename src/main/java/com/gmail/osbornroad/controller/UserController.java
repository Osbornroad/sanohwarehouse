package com.gmail.osbornroad.controller;


import com.gmail.osbornroad.model.jpa.User;
import com.gmail.osbornroad.service.UserService;
import com.gmail.osbornroad.util.ValidationUtil;
import com.gmail.osbornroad.util.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getAllUsers() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all users");
        List<User> userList = userService.findAllUsers();
        return userList;
    }

    @GetMapping(value = "/ajax/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable("id") String stringId) {
        User user;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            user = userService.findUserById(id);
        } catch (NumberFormatException e) {
            user = new User();
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get user: ", user.toString());
        if (getAutorizedUserName().equals(user.getName()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/ajax")
    public ResponseEntity<String> saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        Integer userId = user.getId();
        String rawPassword = user.getPassword();
        if (userId != null) {
            String savedPassword = userService.findUserById(userId).getPassword();
            if (!rawPassword.equals(savedPassword)) {
                user.setPassword(passwordEncoder.encode(rawPassword));
            }
        } else {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save user: ", user.toString());
        try {
            userService.saveUser(user);
        } catch (RegistrationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        if(user != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete user: ", user.toString());
            userService.deleteUser(user);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showUserPage(Model model) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show user page");
        model.addAttribute("allUsersList", userService.findAllUsers());
        return "users";
    }
}
