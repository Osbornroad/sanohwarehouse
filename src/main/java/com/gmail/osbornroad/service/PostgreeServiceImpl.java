package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Recieving;
import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.PostgreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgreeServiceImpl implements PostgreeService {

    @Autowired
    PostgreeRepository postgreeRepository;

    //Shipping service

    @Override
    public int getMaxSavedShippingId() {
        return postgreeRepository.getMaxSavedShippingId();
    }

    @Override
    public int saveShipping(Shipping shipping) {
        return postgreeRepository.saveShipping(shipping);
    }

    //Recieving service


    @Override
    public int getMaxSavedRecievingId() {
        return postgreeRepository.getMaxSavedRecievingId();
    }

    @Override
    public int saveRecieving(Recieving recieving) {
        return postgreeRepository.saveRecieving(recieving);
    }
}
