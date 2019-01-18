package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.dto.ChildPartDTO;
import com.gmail.osbornroad.model.jpa.ChildPart;
import com.gmail.osbornroad.model.jpa.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ChildPartUtil {

    @Autowired
    PartService partService = new PartService();

    public ChildPartDTO getDtoFromChildPart(ChildPart childPart) {

        Part part = partService.findPartById(childPart.getChildPartId());
        String partName = part.getName();
        ChildPartDTO childPartDTO = new ChildPartDTO(
                childPart.getId(),
                childPart.getPart(),
                childPart.getChildPartId(),
                partName,
                childPart.getQuantity()
        );
        return childPartDTO;
    }

    public Set<ChildPartDTO> getSetDtoFromSetChildPart(Set<ChildPart> childPartSet) {

        Set<ChildPartDTO> childPartDTOSet = new HashSet<>();
        for(ChildPart childPart : childPartSet) {
            childPartDTOSet.add(getDtoFromChildPart(childPart));
        }
        return childPartDTOSet;
    }

    public ChildPart getChildPartFromDTO(ChildPartDTO childPartDTO) {

        ChildPart childPart = new ChildPart(
                childPartDTO.getId(),
                childPartDTO.getPart(),
                childPartDTO.getChildPartId(),
                childPartDTO.getQuantity()
        );
        return childPart;
    }

    public Set<ChildPart> getSetChildPartFromSetDto(Set<ChildPartDTO> childPartDTOSet) {

        Set<ChildPart> childPartSet = new HashSet<>();
        for(ChildPartDTO childPartDTO : childPartDTOSet) {
            childPartSet.add(getChildPartFromDTO(childPartDTO));
        }
        return childPartSet;
    }
}
