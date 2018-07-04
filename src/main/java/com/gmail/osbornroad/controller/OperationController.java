package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private static final Logger LOGGER = Logger.getLogger(OperationController.class.getName());

    @Autowired
    private OperationService operationService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String showOperationList (Model model) {
        model.addAttribute("allOperationList", operationService.findAllOperations());
        return "operationList";
    }
}
