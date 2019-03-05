package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.FinishPart;
import com.gmail.osbornroad.model.jdbc.Shipping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@EnableScheduling
public class Scheduler {

    //com.gmail.osbornroad.service.Scheduler

    @Autowired
    FirebirdService firebirdService;

    @Autowired
    PostgreeService postgreeService;

    private static final List<Shipping> currentShippingList = new CopyOnWriteArrayList<>();
    private static final List<FinishPart> CURRENT_FINISH_PART_LIST = new CopyOnWriteArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);


    @Autowired
    PartService partService;

    @Autowired
    OperationService operationService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;









    int counterShipping = 0;

//    @Scheduled(fixedDelay = 5000)
    public void updateShipping() {

//        int lastSavedShippingId = currentShippingList.size() == 0 ? 0 : currentShippingList.get(currentShippingList.size() - 1).getShippingId();
        int lastSavedShippingId = 0;
        long start = System.currentTimeMillis();


//        List<FinishPart> unsavedShippingList = firebirdService.getUnsavedShippingList(lastSavedShippingId);

        long finish = System.currentTimeMillis();
        long dif = finish - start;
        LOGGER.info("difShipping = " + dif);

    }

//    @Scheduled(fixedDelay = 5000)
    public void updateRecieving() {
//        int lastSavedRecievingId = CURRENT_FINISH_PART_LIST.size() == 0 ? 0 : CURRENT_FINISH_PART_LIST.get(CURRENT_FINISH_PART_LIST.size() - 1).getId();
        int lastSavedRecievingId = 0;
        long start = System.currentTimeMillis();

//        List<FinishPart> unsavedFinishPartList = firebirdService.getUnsavedRecievingList(lastSavedRecievingId);
        long finish = System.currentTimeMillis();
        long dif = finish - start;
        LOGGER.info("difRecieving = " + dif);

    }

//    @Scheduled(fixedDelay = 5000)
    public void getRemainingFinishParts() {

/*        long start = System.currentTimeMillis();

        int lastSavedShippingId = 0;
        List<FinishPart> shippingList = firebirdService.getUnsavedShippingList(lastSavedShippingId);

        int lastSavedRecievingId = 0;
        List<FinishPart> recievingList = firebirdService.getUnsavedRecievingList(lastSavedRecievingId);

        List<FinishPart> remainingList = new ArrayList<>();
        for (FinishPart recievingPart : recievingList) {
            int remainingQty = recievingPart.getQuantity();
            for (FinishPart shippingPart : shippingList) {
                if (recievingPart.getPartNumber().equals(shippingPart.getPartNumber())) {
                    remainingQty = remainingQty - shippingPart.getQuantity();
                }
            }
            FinishPart remainingPart = new FinishPart(recievingPart.getPartNumber(), remainingQty);
            remainingList.add(remainingPart);
        }

        long finish = System.currentTimeMillis();
        long dif = finish - start;
        LOGGER.info("Time for getting remaining FG = " + dif);*/
    }

}
