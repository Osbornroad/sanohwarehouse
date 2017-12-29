package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.FinishPart;

import java.util.List;

public interface FirebirdRepository {

    //Shipping repository

/*
    Shipping getShipping(int id);
*/

    List<FinishPart> getUnsavedShippingList(int maxSavedId);

    //FinishPart repository

    List<FinishPart> getUnsavedRecievingList(int maxSavedId);

}
