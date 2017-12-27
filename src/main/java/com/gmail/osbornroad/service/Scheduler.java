package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.ShippingPostgreeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class Scheduler {

    @Autowired
    ShippingService shippingService;

    @Autowired
    ShippingPostgreeService shippingPostgreeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    private int lastFieldKey = 48;

/*    @Scheduled(fixedDelay = 5000)
    public void reportMaxShippingId() {
        int maxShippingId = shippingPostgreeService.getMaxShippingId();
        LOGGER.info("Current MaxShippingId = " + maxShippingId);
    }*/

    @Scheduled(fixedDelay = 20000)
    public void updateShipping() {
        int lastSavedShippingId = shippingPostgreeService.getMaxShippingId();
        List<Shipping> unsavedShipping = shippingService.getUnsavedShipping(lastSavedShippingId);
        if (unsavedShipping.size() == 0) {
            LOGGER.info("There are no new shipping in Firebird database.");
        } else {
            for (Shipping shipping : unsavedShipping) {
                shippingPostgreeService.save(shipping);
            }
        }
    }

/*    @Scheduled(fixedDelay = 3000)
    public void reportNextDelivery(){
        Shipping nextShipping = null;
        try {
            nextShipping = shippingService.get(lastFieldKey);
            LOGGER.info(nextShipping.toString());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            LOGGER.info("No field Key = " + lastFieldKey);
        }
        lastFieldKey++;

        shippingPostgreeService.save(nextShipping);
        }*/




/*    @Scheduled(fixedDelay = 3000)
    public void reportCurrentTime(){
        LOGGER.info("Current time is {}", dateFormat.format(new Date()));
    }*/
}
