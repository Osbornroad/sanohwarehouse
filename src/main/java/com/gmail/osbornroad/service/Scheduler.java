package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Delivery;
import com.gmail.osbornroad.model.Shipping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@EnableScheduling
public class Scheduler {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ShippingService shippingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private int lastFieldKey = 48;

    @Scheduled(fixedDelay = 3000)
    public void reportNextDelivery(){
        try {
            Shipping nextShipping = shippingService.get(lastFieldKey);
            LOGGER.info(nextShipping.toString());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            LOGGER.info("No field Key = " + lastFieldKey);
        }
        lastFieldKey++;

    }

    @Scheduled(fixedDelay = 3000)
    public void reportCurrentTime(){
        LOGGER.info("Current time is {}", dateFormat.format(new Date()));
    }
}
