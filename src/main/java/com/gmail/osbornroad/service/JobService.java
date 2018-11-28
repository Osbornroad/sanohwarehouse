package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Job;
import com.gmail.osbornroad.model.jpa.Part;
import com.gmail.osbornroad.repository.jpa.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Transactional(readOnly = true)
    public List<Job> findAllJobs() {
        List<Job> jobList = new ArrayList<>();
        Iterable<Job> iterable = jobRepository.findAll();
        iterable.forEach(jobList::add);
        return jobList;
    }

    @Transactional(readOnly = true)
    public List<Job> findJobByPart(Part part) {
        List<Job> jobList = jobRepository.findJobByPart(part);
        return jobList;
    }

    @Transactional(readOnly = true)
    public Job findJobById(Integer id) {
        Optional<Job> optional = jobRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Job job) {
        jobRepository.delete(job);
    }
}
