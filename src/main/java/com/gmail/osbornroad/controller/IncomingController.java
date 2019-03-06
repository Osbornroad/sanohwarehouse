package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.FinishPart;
import com.gmail.osbornroad.model.jpa.Incoming;
import com.gmail.osbornroad.service.FinishPartService;
import com.gmail.osbornroad.service.IncomingService;
import com.gmail.osbornroad.util.ValidationUtil;
import com.gmail.osbornroad.util.editor.FinishPartEditor;
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
import java.time.LocalDateTime;
import java.util.List;

import static com.gmail.osbornroad.model.jpa.Role.ROLE_ADMIN;
import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;
import static com.gmail.osbornroad.util.AuthorizedUser.hasRequestedAuthirity;

@Controller
@RequestMapping("/incomings")
public class IncomingController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    IncomingService incomingService;

    @Autowired
    FinishPartService finishPartService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String showIncomingsList(Model model) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show incomings page");
        model.addAttribute("allFinishParts", finishPartService.findAllFinishParts());
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            return "incomings";
        }
        return "main";
    }

    @GetMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Incoming> getAllIncomings() {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all incomings");
        List<Incoming> incomingList = incomingService.findAllIncomings();
        return incomingList;
    }

    @GetMapping(value = "/ajax/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Incoming getIncoming(@PathVariable("id") String stringId) {
        Incoming incoming;
        Integer id;
        try {
            id = Integer.parseInt(stringId);
            incoming = incomingService.findIncomingById(id);
        } catch (NumberFormatException e) {
            incoming = new Incoming();
            incoming.setIncomingDateTime(LocalDateTime.now());
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get incoming: ", incoming.toString());
        return incoming;
    }

    @PostMapping(value = "/ajax")
    public ResponseEntity<String> saveIncoming(@Valid Incoming incoming, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save incoming: ", incoming.toString());
        incomingService.saveIncoming(incoming);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{id}")
    @ResponseBody
    public void deleteIncoming(@PathVariable Integer id) {
        Incoming incoming = incomingService.findIncomingById(id);
        if (incoming != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete incoming: ", incoming.toString());
            incomingService.deleteIncoming(incoming);
        }
    }

/*    class FinishPartEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            int idPosition = text.indexOf("id=");
            String number = text.substring(idPosition + 3, idPosition + 4);
            Integer id = Integer.parseInt(number);
            FinishPart finishPart = finishPartService.findFinishPartById(id);
            setValue(finishPart);
        }
    }*/

    @Autowired
    FinishPartEditor finishPartEditor;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FinishPart.class, finishPartEditor);
    }
}
