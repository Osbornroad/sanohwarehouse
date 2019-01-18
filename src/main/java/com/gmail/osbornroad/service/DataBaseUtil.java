package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.*;
import com.gmail.osbornroad.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataBaseUtil {

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

    @Autowired
    JobService jobService;

    @Autowired
    ChildPartService childPartService;

    public void testSpringJPA() {
        populatePartsTable();
        populateUserTable();
        populateJobTable();
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

    public void populatePartsTable() {
        List<Operation> operationList = new ArrayList<>();
        operationList.add(Operation.HPC);
        operationList.add(Operation.LASER);
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PART_FAKE_DATA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] splitted = line.split(";");
                Part part = new Part(splitted[0], PartType.valueOf(splitted[1])/*, operationList*/);
/*                Map<Integer, Integer> mapChildPart = new HashMap<>();
                mapChildPart.put(2, 1);
                mapChildPart.put(3, 2);
                part.setChildPartMap(mapChildPart);*/
                partService.savePart(part);
            }
        } catch (IOException e) {
        }
    }

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

        //populate child parts

        ChildPart childPart11 = new ChildPart(part1, 3, 1);
        ChildPart childPart12 = new ChildPart(part1, 4, 2);
        ChildPart childPart21 = new ChildPart(part2, 3, 2);
        ChildPart childPart22 = new ChildPart(part2, 5, 1);
        ChildPart childPart23 = new ChildPart(part2, 8, 5);

        childPartService.saveChildPart(childPart11);
        childPartService.saveChildPart(childPart12);
        childPartService.saveChildPart(childPart21);
        childPartService.saveChildPart(childPart22);
        childPartService.saveChildPart(childPart23);
    }

}
