package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    ShippingRepository shippingRepository;

    @Override
    public Shipping get(int id) {
        return shippingRepository.get(id);
    }

    @Override
    public List<Shipping> getAll() {
        return shippingRepository.getAll();
    }
}
