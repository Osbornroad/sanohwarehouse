package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jdbc.FinishPart;
import com.gmail.osbornroad.repository.FirebirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebirdServiceImpl implements FirebirdService {

    @Autowired
    FirebirdRepository firebirdRepository;

    //Shipping service

/*
    @Override
    public Shipping get(int id) {
        return firebirdRepository.getShipping(id);
    }
*/

    @Override
    public List<FinishPart> getUnsavedShippingList(int lastSavedShippingId) {
        return firebirdRepository.getUnsavedShippingList(lastSavedShippingId);
    }

    //FinishPart service

    @Override
    public List<FinishPart> getUnsavedRecievingList(int lastSavedRecievingId) {
        return firebirdRepository.getUnsavedRecievingList(lastSavedRecievingId);
    }
}
