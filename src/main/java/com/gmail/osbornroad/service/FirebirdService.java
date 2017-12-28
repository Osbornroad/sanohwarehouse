package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Recieving;
import com.gmail.osbornroad.model.Shipping;

import java.util.List;

public interface FirebirdService {

    //Shipping service

/*
    Shipping get(int id);
*/

    List<Shipping> getUnsavedShippingList(int lastSavedShippingId);

    //Receiving service

    List<Recieving> getUnsavedRecievingList(int lastSavedRecievingId);

}
