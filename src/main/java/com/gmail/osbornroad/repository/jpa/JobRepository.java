package com.gmail.osbornroad.repository.jpa;

import com.gmail.osbornroad.model.jpa.Job;
import com.gmail.osbornroad.model.jpa.Part;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobRepository extends CrudRepository<Job, Integer> {

    List<Job> findJobByPart(Part part);
}
