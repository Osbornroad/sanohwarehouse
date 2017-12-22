package com.gmail.osbornroad;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements MainInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public Main() {
        LOGGER.info("Main constructor...");
//        printTestInfo();
    }

    public void printTestInfo() {
        LOGGER.info("printTestInfo() работает");
        System.out.println("It is working...");
        System.out.println("It is working...");
        System.out.println("It is working...");
        System.out.println("It is working...");
        System.out.println("It is working...");
    }
}
