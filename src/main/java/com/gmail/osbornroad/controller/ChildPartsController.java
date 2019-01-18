package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.dto.ChildPartDTO;
import com.gmail.osbornroad.model.jpa.ChildPart;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.service.ChildPartUtil;
import com.gmail.osbornroad.service.PartService;
import javafx.scene.effect.SepiaTone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static com.gmail.osbornroad.model.jpa.Role.ROLE_ADMIN;
import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;
import static com.gmail.osbornroad.util.AuthorizedUser.hasRequestedAuthirity;

@Controller
@RequestMapping("/childParts/{partId}")
public class ChildPartsController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    private PartService partService;

    @Autowired
    ChildPartUtil childPartUtil;

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<ChildPartDTO> getAllChildPartsByPart(@PathVariable Integer partId) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all child parts for part");
        Part part = new Part();
        Set<ChildPart> childPartSet = new HashSet<>();
        if (partId != null) {
            part = partService.findPartById(partId);
            childPartSet = part.getChildPartSet();
        }
        Set<ChildPartDTO> childPartDTOSet = childPartUtil.getSetDtoFromSetChildPart(childPartSet);
        return childPartDTOSet;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showChildPartsList(Model model, @PathVariable Integer partId) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show child parts page");
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            if (partId != null) {
                model.addAttribute("partId", partId);
                model.addAttribute("partName", partService.findPartById(partId).getName());
                model.addAttribute("allParts", partService.findAllParts());
            }
            return "childParts";
        }
        return "childParts";
    }
}
