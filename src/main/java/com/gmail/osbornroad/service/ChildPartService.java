package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.ChildPart;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.repository.jpa.ChildPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChildPartService {

    @Autowired
    ChildPartRepository childPartRepository;

    @Transactional(readOnly = true)
    public List<ChildPart> findAllChildParts() {
        List<ChildPart> childPartList = new ArrayList<>();
        Iterable<ChildPart> iterable = childPartRepository.findAll();
        iterable.forEach(childPartList::add);
        return childPartList;
    }

    @Transactional(readOnly = true)
    public List<ChildPart> findChildPartsByPart(Part part) {
        List<ChildPart> childPartList = childPartRepository.findChildPartByPart(part);
        return childPartList;
    }

    @Transactional(readOnly = true)
    public ChildPart findChildPartById(Integer id) {
        Optional<ChildPart> optional = childPartRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public ChildPart saveChildPart(ChildPart childPart) {
        return childPartRepository.save(childPart);
    }

    public void deleteChildPart(ChildPart childPart) {
        childPartRepository.delete(childPart);
    }
}
