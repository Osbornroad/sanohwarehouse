package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.FinishPart;
import com.gmail.osbornroad.model.jpa.Project;
import com.gmail.osbornroad.model.jpa.Variant;
import com.gmail.osbornroad.service.FinishPartService;
import com.gmail.osbornroad.service.VariantService;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;

import static com.gmail.osbornroad.model.jpa.Role.ROLE_ADMIN;
import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;
import static com.gmail.osbornroad.util.AuthorizedUser.hasRequestedAuthirity;

@Controller
@RequestMapping("/variants")
public class VariantController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    VariantService variantService;

    @Autowired
    FinishPartService finishPartService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showVariantList(Model model) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show variant page");
        model.addAttribute("projectList", Project.getProjectList());
        model.addAttribute("finishPartList", finishPartService.findAllFinishParts());
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            return "variants";
        }
        return "main";
    }

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Variant> getAllVariants() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all finish parts");
        List<Variant> variantList = variantService.findAllVariants();
        return variantList;
    }

    @GetMapping(value = "/ajax/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Variant getVariant(@PathVariable("id") String stringId) {
        Variant variant;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            variant = variantService.findVariantById(id);
        } catch (NumberFormatException e) {
            variant = new Variant();
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get variant: ", variant.toString());
        return variant;
    }

    @PostMapping(value = "/ajax")
    public ResponseEntity<String> saveVariant(@Valid Variant variant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save variant: ", variant.toString());
        variantService.saveVariant(variant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{id}")
    @ResponseBody
    public void deleteVariant(@PathVariable Integer id) {
        Variant variant = variantService.findVariantById(id);
        if (variant != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete variant: ", variant.toString());
            variantService.deleteVariant(variant);
        }
    }

    class FinishPartEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            int idPosition = text.indexOf("id=");
            String number = text.substring(idPosition + 3, idPosition + 4);
            Integer id = Integer.parseInt(number);
            FinishPart finishPart = finishPartService.findFinishPartById(id);
            setValue(finishPart);
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FinishPart.class, new FinishPartEditor());
    }
}
