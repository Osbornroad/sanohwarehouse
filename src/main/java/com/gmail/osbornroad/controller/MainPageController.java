package com.gmail.osbornroad.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainPageController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showMainPage() {
        return "main";
    }
}
