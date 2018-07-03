package com.gmail.osbornroad.repository;

import com.gmail.osbornroad.model.jdbc.Shipping;

public interface PostgreeRepository {

    //Shipping repository

    int saveShipping(Shipping shipping);

    int getMaxSavedShippingId();

    //FinishPart repository

//    int saveRecieving(FinishPart recieving);

    int getMaxSavedRecievingId();

    //
}
