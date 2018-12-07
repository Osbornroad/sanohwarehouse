package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.service.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gmail.osbornroad.model.jpa.Role.ROLE_ADMIN;
import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;
import static com.gmail.osbornroad.util.AuthorizedUser.hasRequestedAuthirity;

@Controller
@RequestMapping("/childParts")
public class ChildPartsController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    private PartService partService;

    @GetMapping(value = "/{partId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String showChildPartsList(Model model, @PathVariable Integer partId) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show child parts page");
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            if (partId != null) {
                model.addAttribute("partName", partService.findPartById(partId).getName());
            }
            return "childParts";
        }
        return "childParts";
    }
}
