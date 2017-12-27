package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.Shipping;

import java.util.List;

public interface ShippingRepository {

    Shipping get(int id);

    List<Shipping> getAll();

    List<Shipping> getUnsavedShippingId(int startId);
}
