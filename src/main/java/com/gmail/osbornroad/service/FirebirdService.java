package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jdbc.FinishPart;

import java.util.List;

public interface FirebirdService {

    //Shipping service

/*
    Shipping get(int id);
*/

    List<FinishPart> getUnsavedShippingList(int lastSavedShippingId);

    //Receiving service

    List<FinishPart> getUnsavedRecievingList(int lastSavedRecievingId);

}
