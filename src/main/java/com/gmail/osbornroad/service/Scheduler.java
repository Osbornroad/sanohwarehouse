package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Recieving;
import com.gmail.osbornroad.model.Shipping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@EnableScheduling
public class Scheduler {

    @Autowired
    FirebirdService firebirdService;

    @Autowired
    PostgreeService postgreeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedDelay = 20000)
    public void updateShipping() {
        int lastSavedShippingId = postgreeService.getMaxSavedShippingId();
        List<Shipping> unsavedShippingList = firebirdService.getUnsavedShippingList(lastSavedShippingId);
        if (unsavedShippingList.size() == 0) {
            LOGGER.info("There are no new shipping in Firebird database.");
        } else {
            for (Shipping shipping : unsavedShippingList) {
                postgreeService.saveShipping(shipping);
            }
        }
    }

    @Scheduled(fixedDelay = 20000)
    public void updateRecieving() {
        int lastSavedRecievingId = 0;
        List<Recieving> unsavedRecievingList = firebirdService.getUnsavedRecievingList(lastSavedRecievingId);
        if (unsavedRecievingList.size() == 0) {
            LOGGER.info("There are no new recieving in Firebird database.");
        }
        else {
            for (Recieving recieving : unsavedRecievingList) {
                postgreeService.saveRecieving(recieving);
            }
        }
    }

}
