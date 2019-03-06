package com.gmail.osbornroad.util.editor;

import com.gmail.osbornroad.model.jpa.FinishPart;
import com.gmail.osbornroad.service.FinishPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class FinishPartEditor extends PropertyEditorSupport {

    @Autowired
    FinishPartService finishPartService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        int idPosition = text.indexOf("id=");
        String number = text.substring(idPosition + 3, idPosition + 4);
        Integer id = Integer.parseInt(number);
        FinishPart finishPart = finishPartService.findFinishPartById(id);
        setValue(finishPart);
    }
}