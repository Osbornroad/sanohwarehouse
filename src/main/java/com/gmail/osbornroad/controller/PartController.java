package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.service.OperationService;
import com.gmail.osbornroad.service.PartService;
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
@RequestMapping("/parts")
public class PartController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    private PartService partService;

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Part> getAllAjaxParts() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all parts");
        List<Part> partList = partService.findAllParts();
        return partList;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showPartsList(Model model) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show parts page");
        model.addAttribute("allPartList", partService.findAllParts());
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            return "parts";
        }
        return "parts";
    }
}
