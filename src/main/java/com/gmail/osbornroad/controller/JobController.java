package com.gmail.osbornroad.controller;

import com.gmail.osbornroad.model.jpa.Job;
import com.gmail.osbornroad.model.jpa.Operation;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.service.JobService;
import com.gmail.osbornroad.service.PartService;
import com.gmail.osbornroad.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.gmail.osbornroad.model.jpa.Role.ROLE_ADMIN;
import static com.gmail.osbornroad.util.AuthorizedUser.getAutorizedUserName;
import static com.gmail.osbornroad.util.AuthorizedUser.hasRequestedAuthirity;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private static final Logger LOGGER = LoggerFactory.getLogger("osbornroad");

    @Autowired
    private JobService jobService;

    @Autowired
    private PartService partService;

/*    @GetMapping(value = "/ajax/{partId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Job> getAllJobs(@PathVariable Integer partId) {
        List<Job> jobList = new ArrayList<>();
        Part part = new Part();
        if (partId != null) {
            part = partService.findPartById(partId);
            jobList = jobService.findJobByPart(part);
        }
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all jobs for part " + part);
        return jobList;
    }*/

    @GetMapping(value = "/ajax/{partId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<Job> getAllJobsByPart(@PathVariable Integer partId) {
        Set<Job> jobSet = new HashSet<>();
        Part part = new Part();
        if (partId != null) {
            part = partService.findPartById(partId);
            jobSet = part.getJobSet();
        }
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "get all jobs for part " + part);
        return jobSet;
    }

    @GetMapping(value = "/ajax/{partId}/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Job getJob(@PathVariable("partId") Integer partId, @PathVariable("jobId") Integer jobId) {
        Job job;
        try {
            job = jobService.findJobById(jobId);
        } catch (NumberFormatException e) {
            job = new Job();
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "get job: ", job.toString());
        return job;
    }

    @PostMapping(value = "/ajax/{partId}/")
    public ResponseEntity<String> saveJob(@PathVariable("partId") Integer partId, @Valid Job job, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.getErrorResponse(bindingResult);
        }
        LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "save job: ", job.toString());
        if (job.getPart() == null) {
            job.setPart(partService.findPartById(partId));
        }
        jobService.saveJob(job);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/ajax/{partId}/{jobId}")
    @ResponseBody
    public void deleteJob(@PathVariable Integer partId, @PathVariable Integer jobId) {
        Job job = jobService.findJobById(jobId);
        if (job != null) {
            LOGGER.info("{} - User: {} - {}{}", getClass().getSimpleName(), getAutorizedUserName(), "delete job: ", job.toString());
            jobService.deleteJob(job);
        }
    }

    @GetMapping(value = "/{partId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String showJobList(Model model, @PathVariable Integer partId) {
        LOGGER.info("{} - User: {} - {}", getClass().getSimpleName(), getAutorizedUserName(), "show jobs page");
//        model.addAttribute("allJobList", jobService.findAllJobs());
        if (hasRequestedAuthirity(ROLE_ADMIN.getAuthority())) {
            if (partId != null) {
                model.addAttribute("partId", partId);
                model.addAttribute("partName", partService.findPartById(partId).getName());
                model.addAttribute("operationList", Operation.getOperationList());
            }
            return "jobs";
        }
        return "jobsForUsers";
    }
}
