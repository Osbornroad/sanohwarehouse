package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.Operation;
import com.gmail.osbornroad.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.Inet4Address;
import java.util.logging.Logger;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private static final Logger LOGGER = Logger.getLogger(OperationController.class.getName());

    @Autowired
    private OperationService operationService;

    @RequestMapping(method = RequestMethod.GET)
    public String showOperationList (Model model) {
        model.addAttribute("allOperationList", operationService.findAllOperations());
        return "operationList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getOperation (@PathVariable("id") Integer id, Model model) {
        model.addAttribute("operation", operationService.findOperationById(id));
        return "operation";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String saveOperation (@PathVariable("id") Integer id, @Valid Operation operation) {
        if (operation != null) {
            operationService.saveOperation(operation);
        }
        return "redirect:/operations";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
    public String deleteOperation (@PathVariable Integer id) {
        Operation operation = operationService.findOperationById(id);
        if (operation != null) {
            operationService.deleteOperation(operation);
        }
        return "redirect:/operations";
    }
}
