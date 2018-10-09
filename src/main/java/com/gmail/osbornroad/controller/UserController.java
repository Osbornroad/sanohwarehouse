package com.gmail.osbornroad.controller;


import com.gmail.osbornroad.model.jpa.User;
import com.gmail.osbornroad.service.UserService;
import com.gmail.osbornroad.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> userList = userService.findAllUsers();
        return userList;
    }

    @GetMapping(value = "/ajax/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getUser(@PathVariable("id") String stringId) {
        User user;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            user = userService.findUserById(id);
        } catch (NumberFormatException e) {
            user = new User();
        }
        return user;
    }

    @PostMapping(value = "/ajax")
    public ResponseEntity<String> saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        if(user != null) {
            userService.deleteUser(user);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showUserPage(Model model) {
        model.addAttribute("allUsersList", userService.findAllUsers());
        return "users";
    }
}
