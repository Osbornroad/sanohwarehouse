package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.repository.jpa.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PartService {

    @Autowired
    PartRepository partRepository;

    @Transactional(readOnly = true)
    public List<Part> findAllParts() {
        List<Part> partList = new ArrayList<>();
        Iterable<Part> iterable = partRepository.findAll();
        iterable.forEach(partList::add);
        return partList;
    }

    @Transactional(readOnly = true)
    public Part findPartById(Integer id) {
        Optional<Part> optional = partRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Part savePart(Part part) {
//        Collections.sort(part.getOperationList(), Operation.operationComparator);
        return partRepository.save(part);
    }

    public void deletePart(Part part) {
        partRepository.delete(part);
    }
}
