package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.Shipping;

import java.util.List;

public interface ShippingPostgreeRepository {
    Shipping get(int id);

    List<Shipping> getAll();

    boolean delete(int id);

    boolean save(Shipping shipping);

    boolean update(Shipping shipping);
}
