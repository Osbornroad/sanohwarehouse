package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jdbc.FinishPart;
import com.gmail.osbornroad.model.jdbc.Shipping;
import com.gmail.osbornroad.model.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void testSpringJPA() {
//        populateOperationTable();
        populatePartsTable();
        populateUserTable();
        populateJobTable();
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

        String maksimPassword = passwordEncoder.encode("111111");
        String pavelPassword = passwordEncoder.encode("222222");

        User admin = new User("Maksim", "maksim.tkachenko@sanoh-rus.com", maksimPassword, true, adminRegistered, adminRoles);
        User noAdmin = new User("Pavel", "pavel.yulin@sanoh-rus.com", pavelPassword, true, noAdminRegistered, notAdminRoles);

        userService.saveUser(admin);
        userService.saveUser(noAdmin);
    }

/*    public void populateOperationTable() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_OPERATION_FAKE_DATA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] splitted = line.split(";");
                operationService.saveOperation(new Operation(splitted[0], Integer.parseInt(splitted[1])));
            }
        } catch (IOException e) {
        }
    }*/

    public void populatePartsTable() {
        List<Operation> operationList = new ArrayList<>();
        operationList.add(Operation.HPC);
        operationList.add(Operation.LASER);
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PART_FAKE_DATA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] splitted = line.split(";");
                partService.savePart(new Part(splitted[0], PartType.valueOf(splitted[1])/*, operationList*/));
            }
        } catch (IOException e) {
        }
    }

    @Autowired
    JobService jobService;

    public void populateJobTable() {
        Part part1 = partService.findPartById(1);
        Part part2 = partService.findPartById(2);
        Job job11 = new Job(part1, Operation.HPC, Machine.HPC, 10);
        Job job12 = new Job(part1, Operation.BRUSHING, Machine.BRUSHING, 8);
        Job job21 = new Job(part2, Operation.ENDFORMING, Machine.ENDFORMING_LONG, 20);
        Job job22 = new Job(part2, Operation.ENDFORMING, Machine.ENDFORMING_SHORT, 21);
        Job job23 = new Job(part2, Operation.LASER, Machine.LASER, 12);

        jobService.saveJob(job11);
        jobService.saveJob(job12);
        jobService.saveJob(job21);
        jobService.saveJob(job22);
        jobService.saveJob(job23);
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
