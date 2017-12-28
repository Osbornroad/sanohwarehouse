package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.Recieving;
import com.gmail.osbornroad.model.Shipping;

import java.util.List;

public interface FirebirdRepository {

    //Shipping repository

/*
    Shipping getShipping(int id);
*/

    List<Shipping> getUnsavedShippingList(int maxSavedId);

    //Recieving repository

    List<Recieving> getUnsavedRecievingList(int maxSavedId);

}
