package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.FinishPart;
import com.gmail.osbornroad.repository.jpa.FinishPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FinishPartService {

    @Autowired
    FinishPartRepository finishPartRepository;

    @Transactional(readOnly = true)
    public List<FinishPart> findAllFinishParts() {
        List<FinishPart> finishPartList = new ArrayList<>();
        Iterable<FinishPart> iterable = finishPartRepository.findAll();
        iterable.forEach(finishPartList::add);
        return finishPartList;
    }

    @Transactional(readOnly = true)
    public FinishPart findFinishPartById(Integer id) {
        Optional<FinishPart> optional = finishPartRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public FinishPart saveFinishPart(FinishPart finishPart) {
        return finishPartRepository.save(finishPart);
    }

    public void deleteFinishPart(FinishPart finishPart) {
        finishPartRepository.delete(finishPart);
    }
}
