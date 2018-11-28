package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.Operation;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.service.PartService;
import com.gmail.osbornroad.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public List<Part> getAllParts() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all parts");
        List<Part> partList = partService.findAllParts();
        return partList;
    }

    @GetMapping(value = "/ajax/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Part getPart(@PathVariable("id") String stringId/*, Model model*/) {
        Part part;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            part = partService.findPartById(id);
        } catch (NumberFormatException e) {
            part = new Part();
        }
//        List<String> shortOpNames = new ArrayList<>();

//        model.addAttribute("shortOpNames", shortOpNames);
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get part: ", part.toString());
        return part;
    }

    @PostMapping(value = "/ajax")
    public ResponseEntity<String> savePart(@Valid Part part, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save part: ", part.toString());
        partService.savePart(part);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{id}")
    @ResponseBody
    public void deletePart(@PathVariable Integer id) {
        Part part = partService.findPartById(id);
        if (part != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete part: ", part.toString());
            partService.deletePart(part);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showPartsList(Model model) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show parts page");
        model.addAttribute("allPartList", partService.findAllParts());
        model.addAttribute("allOperationList", Operation.getOperationsArray());
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            return "parts";
        }
        return "parts";
    }
}
