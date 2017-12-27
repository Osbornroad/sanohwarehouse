package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Shipping;

import java.util.List;

public interface ShippingService {
    Shipping get(int id);

    List<Shipping> getUnsavedShipping(int lastSavedShippingId);
}
