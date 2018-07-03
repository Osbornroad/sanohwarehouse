package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Operation;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.repository.jpa.OperationRepository;
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
public class OperationService {

    @Autowired
    OperationRepository operationRepository;

    @Transactional(readOnly = true)
    public List<Operation> findAllOperations() {
        List<Operation> operationList = new ArrayList<>();
        Iterable<Operation> iterable = operationRepository.findAll();
        iterable.forEach(operationList::add);
        return operationList;
    }

    @Transactional(readOnly = true)
    public Operation findOperationById(Integer id) {
        Optional<Operation> optional = operationRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }
}
