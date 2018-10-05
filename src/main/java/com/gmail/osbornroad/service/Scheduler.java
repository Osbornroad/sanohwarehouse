package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jdbc.FinishPart;
import com.gmail.osbornroad.model.jdbc.Shipping;
import com.gmail.osbornroad.model.jpa.Operation;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.model.jpa.Role;
import com.gmail.osbornroad.model.jpa.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String PATH_TO_OPERATION_FAKE_DATA =
            "C:\\Users\\User\\IdeaProjects\\sanohwarehouse\\src\\main\\resources\\fakedata\\operationsFakeData.txt";
    private static final String PATH_TO_PART_FAKE_DATA =
            "C:\\Users\\User\\IdeaProjects\\sanohwarehouse\\src\\main\\resources\\fakedata\\partsFakeData.txt";

    @Autowired
    PartService partService;

    @Autowired
    OperationService operationService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void testSpringJPA() {
        populateOperationTable();
        populatePartsTable();
        populateUserTable();
//        testPartTable();
//        testOperatonTable();
//        testPartOperationJoinTable();
    }

    public void populateUserTable() {
        Set<Role> adminRoles = new HashSet<>();
        LocalDateTime adminRegistered = LocalDateTime.now();
        Set<Role> notAdminRoles = new HashSet<>();
        LocalDateTime noAdminRegistered = LocalDateTime.now();

        adminRoles.add(Role.ROLE_ADMIN);
        adminRoles.add(Role.ROLE_USER);
        notAdminRoles.add(Role.ROLE_USER);

        User admin = new User("Maksim", "maksim.tkachenko@sanoh-rus.com", "111111", adminRegistered, adminRoles);
        User noAdmin = new User("Pavel", "pavel.yulin@sanoh-rus.com", "222222", noAdminRegistered, notAdminRoles);

        userService.saveUser(admin);
        userService.saveUser(noAdmin);
    }

    public void populateOperationTable() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_OPERATION_FAKE_DATA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] splitted = line.split(";");
                operationService.saveOperation(new Operation(splitted[0], Integer.parseInt(splitted[1])));
            }
        } catch (IOException e) {
        }
    }

    public void populatePartsTable() {
        Set<Operation> operationSet = new HashSet<>(operationService.findAllOperations());
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PART_FAKE_DATA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] splitted = line.split(";");
                partService.savePart(new Part(splitted[0], Integer.parseInt(splitted[1]), operationSet));
            }
        } catch (IOException e) {
        }
    }

    public void testPartOperationJoinTable() {
        Set<Operation> operations = new HashSet<>();
        operations.add(operationService.findOperationById(1));
        operations.add(operationService.findOperationById(2));
        operations.add(operationService.findOperationById(4));
        Part part2 = partService.findPartById(2);
        part2.setOperationSet(operations);
        partService.savePart(part2);
        Part part3 = partService.findPartById(3);
        part3.setOperationSet(operations);
        partService.savePart(part3);
        List<Part> partList = partService.findAllParts();
        List<Operation> operationList = operationService.findAllOperations();
        LOGGER.info("1 step");

        part2 = partService.findPartById(2);
        part2.setPartName("Some New part name 2.1");
        partService.savePart(part2);
        partList = partService.findAllParts();
        operationList = operationService.findAllOperations();
        LOGGER.info("2 step");

        Operation operation4 = operationService.findOperationById(4);
        operation4.setName("Some new operation name 4");
        operationService.saveOperation(operation4);
        partList = partService.findAllParts();
        operationList = operationService.findAllOperations();
        LOGGER.info("3 step");

        operationService.deleteOperation(operationService.findOperationById(2));
        partList = partService.findAllParts();
        operationList = operationService.findAllOperations();
        LOGGER.info("4 step");

        partService.deletePart(partService.findPartById(3));
        partList = partService.findAllParts();
        operationList = operationService.findAllOperations();
        LOGGER.info("5 step");
    }

    public void testPartTable () {
        List<Part> partList = partService.findAllParts();
        for (Part part : partList) {
            LOGGER.info(part.toString());
        }
        Part part = partService.findPartById(2);
        LOGGER.info("Get Part #2: " + part.toString());
        part.setPartName("Modified Part Name #2.1");
        partService.savePart(part);
        partService.savePart(new Part("Created Part",50));
        partList = partService.findAllParts();
        LOGGER.info("Modified part table");
        for (Part part1 : partList) {
            LOGGER.info(part1.toString());
        }
    }

    public void testOperatonTable() {
        List<Operation> operationList = operationService.findAllOperations();
        for (Operation operation : operationList) {
            LOGGER.info(operation.toString());
        }
        Operation operation = operationService.findOperationById(3);
        LOGGER.info("Get Operation #3: " + operation.toString());
        operation.setName("Modified Op Name #3.1");
        operation.setOperationSequence(1001);
        operationService.saveOperation(operation);
        operationService.saveOperation(new Operation("Created Operation", 222));
        operationList = operationService.findAllOperations();
        LOGGER.info("Modified operation table");
        for (Operation operation1 : operationList) {
            LOGGER.info(operation1.toString());
        }

    }

    int counterShipping = 0;

//    @Scheduled(fixedDelay = 5000)
    public void updateShipping() {

//        int lastSavedShippingId = currentShippingList.size() == 0 ? 0 : currentShippingList.get(currentShippingList.size() - 1).getShippingId();
        int lastSavedShippingId = 0;
        long start = System.currentTimeMillis();


        List<FinishPart> unsavedShippingList = firebirdService.getUnsavedShippingList(lastSavedShippingId);

        long finish = System.currentTimeMillis();
        long dif = finish - start;
        LOGGER.info("difShipping = " + dif);

    }

//    @Scheduled(fixedDelay = 5000)
    public void updateRecieving() {
//        int lastSavedRecievingId = CURRENT_FINISH_PART_LIST.size() == 0 ? 0 : CURRENT_FINISH_PART_LIST.get(CURRENT_FINISH_PART_LIST.size() - 1).getId();
        int lastSavedRecievingId = 0;
        long start = System.currentTimeMillis();

        List<FinishPart> unsavedFinishPartList = firebirdService.getUnsavedRecievingList(lastSavedRecievingId);
        long finish = System.currentTimeMillis();
        long dif = finish - start;
        LOGGER.info("difRecieving = " + dif);

    }

//    @Scheduled(fixedDelay = 5000)
    public void getRemainingFinishParts() {

        long start = System.currentTimeMillis();

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
        LOGGER.info("Time for getting remaining FG = " + dif);
    }

}
