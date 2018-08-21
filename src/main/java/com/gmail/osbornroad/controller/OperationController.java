package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.Operation;
import com.gmail.osbornroad.service.OperationService;
import com.gmail.osbornroad.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.Inet4Address;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private static final Logger LOGGER = Logger.getLogger(OperationController.class.getName());

    @Autowired
    private OperationService operationService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public String showOperationList (Model model) {
        model.addAttribute("allOperationList", operationService.findAllOperations());
        return "operationList";
    }

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Operation> getAllOperations (Model model) {
        List<Operation> operationList = operationService.findAllOperations();
        return operationList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getOperation (@PathVariable("id") String stringId, Model model) {
        Operation operation;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            operation = operationService.findOperationById(id);
        } catch (NumberFormatException e) {
            operation = new Operation();
        }
        model.addAttribute("operation", operation);
        return "operation";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOperation (@Valid Operation operation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "operation";
        }
        if (operation.getId() != null) {
            operation.setPartSet(operationService.findOperationById(operation.getId()).getPartSet());
        }
        operationService.saveOperation(operation);
        return "redirect:/operations";
    }


    /*@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String updateOperation(Operation operation, BindingResult bindingResult,
                                  Model model, HttpServletRequest httpServletRequest,
                                  RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating operation: " + operation.toString());
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error", messageSource.getMessage("operation_save_fail",
                    new Object[]{}, locale)));
            model.addAttribute("operation", operation);
            return "operation";
        }
        model.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("operation_save_success",
                new Object[]{}, locale)));
        operationService.saveOperation(operation);
        return "redirect:/operations";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateFormOperation (@PathVariable ("id") Integer id, Model model) {
        model.addAttribute("operation", operationService.findOperationById(id));
        return "operation";
    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteOperation (@PathVariable Integer id) {
        Operation operation = operationService.findOperationById(id);
        if (operation != null) {
            operationService.deleteOperation(operation);
        }
    }

    @RequestMapping(value = "/ajax/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAjaxOperation (@PathVariable Integer id) {
        Operation operation = operationService.findOperationById(id);
        if (operation != null) {
            operationService.deleteOperation(operation);
        }
    }
}
