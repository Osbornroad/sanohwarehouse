package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.Shipping;

public interface PostgreeRepository {

    //Shipping repository

    int saveShipping(Shipping shipping);

    int getMaxSavedShippingId();

    //FinishPart repository

//    int saveRecieving(FinishPart recieving);

    int getMaxSavedRecievingId();

    //
}
