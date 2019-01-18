package com.gmail.osbornroad.repository.jpa;

import com.gmail.osbornroad.model.jpa.ChildPart;
import com.gmail.osbornroad.model.jpa.Part;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChildPartRepository extends CrudRepository<ChildPart, Integer> {

    List<ChildPart> findChildPartByPart(Part part);
}
