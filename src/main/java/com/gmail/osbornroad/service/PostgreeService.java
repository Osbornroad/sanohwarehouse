package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Recieving;
import com.gmail.osbornroad.model.Shipping;

public interface PostgreeService {

    //Shipping service

    int getMaxSavedShippingId();

    int saveShipping(Shipping shipping);

    //Recieving repository

    int getMaxSavedRecievingId();

    int saveRecieving(Recieving recieving);

}
