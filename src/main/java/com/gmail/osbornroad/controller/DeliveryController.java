package com.gmail.osbornroad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);
/*
    @Autowired
    private DeliveryService deliveryService;

    @RequestMapping({"delivery"})
    public String getTestDelivery(Model model) {
        LOGGER.info("DeliveryController.getTestDelivery() working...");
//        model.addAttribute("testDelivery", "Fake Delivery");
        return "delivery";
    }

    @RequestMapping({"delivery/getinfo"})
    public String getTestDeliveryInfo(Model model) {
        Delivery testDelivery = deliveryService.getShipping(3465);
        LOGGER.info("DeliveryController.getTestDeliveryInfo() working...");
        model.addAttribute("testDelivery", testDelivery);
        return "delivery";
    }

    @RequestMapping({"delivery/getallinfo"})
    public String getTestAllDeliveryInfo(Model model) {
        List<Delivery> testAllDelivery = deliveryService.getAllShipping();
        LOGGER.info("DeliveryController.getTestDeliveryInfo() working...");
        model.addAttribute("testAllDelivery", testAllDelivery);
        return "delivery";
    }*/

/*    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "hello";

    }*/

}
