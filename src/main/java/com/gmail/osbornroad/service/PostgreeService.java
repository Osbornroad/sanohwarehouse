package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jdbc.Shipping;

public interface PostgreeService {

    //Shipping service

    int getMaxSavedShippingId();

    int saveShipping(Shipping shipping);

    //FinishPart repository

/*    int getMaxSavedRecievingId();

    int saveRecieving(FinishPart recieving);*/

}
