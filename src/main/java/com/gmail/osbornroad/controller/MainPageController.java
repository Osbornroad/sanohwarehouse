package com.gmail.osbornroad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;

@Controller
@RequestMapping("/main")
public class MainPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showMainPage() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show main page");
        return "main";
    }*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView showMainPage(@RequestParam(value = "response403", required = false) String response403) {


        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show main page");

        ModelAndView model = new ModelAndView();
        if (response403 != null) {
            model.addObject("response403",
                    "<strong>User: " + getAutorizedUserName() + "!</strong> You do not have permission to access this page!");
        }

        return model;
    }
}
