package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.Operation;
import com.gmail.osbornroad.service.OperationService;
import com.gmail.osbornroad.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.gmail.osbornroad.model.jpa.Role.ROLE_ADMIN;
import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;
import static com.gmail.osbornroad.util.AuthorizedUser.hasRequestedAuthirity;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    private OperationService operationService;

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Operation> getAjaxAllOperations() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all operations");
        List<Operation> operationList = operationService.findAllOperations();
        return operationList;
    }

    @GetMapping(value = "/ajax/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Operation getAjaxOperation(@PathVariable("id") String stringId) {
        Operation operation;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            operation = operationService.findOperationById(id);
        } catch (NumberFormatException e) {
            operation = new Operation();
        }
        /*if (operation == null) {
            operation = new Operation();
        }*/
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get operation: ", operation);
        return operation;
    }

    @PostMapping(value = "/ajax")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> saveAjaxOperation (@Valid Operation operation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        if (operation.getId() != null) {
            operation.setPartSet(operationService.findOperationById(operation.getId()).getPartSet());
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save operation: ", operation.toString());
        operationService.saveOperation(operation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAjaxOperation (@PathVariable Integer id) {
        Operation operation = operationService.findOperationById(id);
        if (operation != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete operation: ", operation);
            operationService.deleteOperation(operation);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showOperationList(Model model) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show operation page");
        model.addAttribute("allOperationList", operationService.findAllOperations());
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            return "operations";
        }
        return "operationsForUsers";
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

/*    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteOperation (@PathVariable Integer id) {
        Operation operation = operationService.findOperationById(id);
        if (operation != null) {
            operationService.deleteOperation(operation);
        }
    }*/

/*    @PostMapping
    public String saveOperation (@Valid Operation operation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "operation";
        }
        if (operation.getId() != null) {
            operation.setPartSet(operationService.findOperationById(operation.getId()).getPartSet());
        }
        operationService.saveOperation(operation);
        return "redirect:/operations";
    }*/

/*    @GetMapping(value = "/{id}")
    public String getOperation(@PathVariable("id") String stringId, Model model) {
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
    }*/

}
