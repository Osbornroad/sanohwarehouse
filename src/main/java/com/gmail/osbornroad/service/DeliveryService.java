package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Delivery;

import java.util.List;

public interface DeliveryService {

    Delivery get(int id);

    List<Delivery> getAll();
}
