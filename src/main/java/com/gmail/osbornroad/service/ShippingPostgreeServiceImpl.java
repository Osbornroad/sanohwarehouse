package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.ShippingPostgreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingPostgreeServiceImpl implements ShippingPostgreeService {

    @Autowired
    ShippingPostgreeRepository shippingPostgreeRepository;

    @Override
    public Shipping get(int id) {
        return null;
    }

    @Override
    public List<Shipping> getAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean save(Shipping shipping) {

        return shippingPostgreeRepository.save(shipping);
    }

    @Override
    public boolean update(Shipping shipping) {
        return false;
    }
}
