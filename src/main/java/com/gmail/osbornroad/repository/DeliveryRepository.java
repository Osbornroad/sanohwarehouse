package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.Delivery;

import java.util.List;

public interface DeliveryRepository {

    Delivery get(int id);

    List<Delivery> getAll();
}
