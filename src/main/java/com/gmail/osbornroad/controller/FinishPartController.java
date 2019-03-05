package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.FinishPart;
import com.gmail.osbornroad.model.jpa.PartType;
import com.gmail.osbornroad.service.FinishPartService;
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
import java.util.List;

import static com.gmail.osbornroad.model.jpa.Role.ROLE_ADMIN;
import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;
import static com.gmail.osbornroad.util.AuthorizedUser.hasRequestedAuthirity;

@Controller
@RequestMapping("/finishParts")
public class FinishPartController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    private FinishPartService finishPartService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showFinishPartList(Model model) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show finish parts page");
        model.addAttribute("allFinishPartList", finishPartService.findAllFinishParts());
        model.addAttribute("partTypeList", PartType.getPartTypeList());
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            return "finishParts";
        }
        return "main";
    }

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FinishPart> getAllFinishParts() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all finish parts");
        List<FinishPart> finishPartList = finishPartService.findAllFinishParts();
        return finishPartList;
    }

    @GetMapping(value = "/ajax/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FinishPart getFinishPart(@PathVariable("id") String stringId) {
        FinishPart finishPart;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            finishPart = finishPartService.findFinishPartById(id);
        } catch (NumberFormatException e) {
            finishPart = new FinishPart();
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get finish part: ", finishPart.toString());
        return finishPart;
    }

    @PostMapping(value = "/ajax")
    public ResponseEntity<String> saveFinishPart(@Valid FinishPart finishPart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save finish part: ", finishPart.toString());
        if (finishPart.getId() != null) {
            if (finishPart.getVariantSet() == null || finishPart.getVariantSet().size() == 0) {
                FinishPart savedFinishPart = finishPartService.findFinishPartById(finishPart.getId());
                if (savedFinishPart.getVariantSet() != null || savedFinishPart.getVariantSet().size() > 0) {
                    finishPart.setVariantSet(savedFinishPart.getVariantSet());
                }
            }
        }
        finishPartService.saveFinishPart(finishPart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{id}")
    @ResponseBody
    public void deleteFinishPart(@PathVariable Integer id) {
        FinishPart finishPart = finishPartService.findFinishPartById(id);
        if (finishPart != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete finish part: ", finishPart.toString());
            finishPartService.deleteFinishPart(finishPart);
        }
    }
}
