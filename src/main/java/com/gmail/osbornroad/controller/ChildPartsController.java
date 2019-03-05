package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.dto.ChildPartDTO;
import com.gmail.osbornroad.model.jpa.ChildPart;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.service.ChildPartService;
import com.gmail.osbornroad.service.ChildPartUtil;
import com.gmail.osbornroad.service.PartService;
import com.gmail.osbornroad.util.ValidationUtil;
import javafx.scene.effect.SepiaTone;
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
    ChildPartService childPartService;

    @Autowired
    ChildPartUtil childPartUtil;

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<ChildPartDTO> getAllChildPartsDtoByPart(@PathVariable Integer partId) {
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

    @GetMapping(value = "/ajax/{childPartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ChildPartDTO> getChildPartDto(@PathVariable("partId") Integer partId, @PathVariable("childPartId") String stringId){
        ChildPartDTO childPartDTO;
        Integer childPartId;
        try {
            childPartId = Integer.parseInt(stringId);
            childPartDTO = childPartUtil.getDtoFromChildPart(childPartService.findChildPartById(childPartId));
        } catch (NumberFormatException e) {
            childPartDTO = new ChildPartDTO();
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get child part DTO: ", childPartDTO.toString());
        return new ResponseEntity<>(childPartDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/ajax")
    public ResponseEntity<String> saveChildPart(@PathVariable("partId") Integer partId,
                                                @Valid ChildPartDTO childPartDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save child part DTO: ", childPartDTO.toString());
        if (childPartDTO.getPart() == null) {
            childPartDTO.setPart(partService.findPartById(partId));
        }
        ChildPart childPart = childPartUtil.getChildPartFromDTO(childPartDTO);
        childPartService.saveChildPart(childPart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{childPartId}")
    @ResponseBody
    public void deleteChildPart(@PathVariable Integer partId, @PathVariable Integer childPartId) {
        ChildPart childPart = childPartService.findChildPartById(childPartId);
        if (childPart != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete childPart: ", childPart.toString());
            childPartService.deleteChildPart(childPart);
        }
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
