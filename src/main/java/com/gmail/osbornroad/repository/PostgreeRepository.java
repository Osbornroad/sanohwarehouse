package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.Recieving;
import com.gmail.osbornroad.model.Shipping;

public interface PostgreeRepository {

    //Shipping repository

    int saveShipping(Shipping shipping);

    int getMaxSavedShippingId();

    //Recieving repository

    int saveRecieving(Recieving recieving);

    int getMaxSavedRecievingId();

    //
}
