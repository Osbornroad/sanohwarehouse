package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Incoming;
import com.gmail.osbornroad.repository.jpa.IncomingRepository;
import com.gmail.osbornroad.repository.jpa.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IncomingService {

    @Autowired
    IncomingRepository incomingRepository;

    @Transactional(readOnly = true)
    public List<Incoming> findAllIncomings() {
        List<Incoming> incomingList = new ArrayList<>();
        Iterable<Incoming> iterable = incomingRepository.findAll();
        iterable.forEach(incomingList::add);
        return incomingList;
    }

    @Transactional(readOnly = true)
    public Incoming findIncomingById(Integer id) {
        Optional<Incoming> optional = incomingRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Incoming saveIncoming(Incoming incoming) {
        return incomingRepository.save(incoming);
    }

    public void deleteIncoming(Incoming incoming) {
        incomingRepository.delete(incoming);
    }
}
