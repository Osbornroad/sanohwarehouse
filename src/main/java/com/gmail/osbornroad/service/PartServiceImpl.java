package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.repository.jpa.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Repository
@Transactional
public class PartServiceImpl implements PartService {

    @Autowired
    PartRepository partRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Part> findAllParts() {
        List<Part> partList = new ArrayList<>();
        Iterable<Part> iterable = partRepository.findAll();
        iterable.forEach(partList::add);
        return partList;
    }

    @Transactional(readOnly = true)
    @Override
    public Part findPartById(Integer id) {
        Optional<Part> optional = partRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    @Override
    public void deletePart(Part part) {
        partRepository.delete(part);
    }
}
