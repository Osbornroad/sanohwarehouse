package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Part;

import java.util.List;

public interface PartService {
    List<Part> findAllParts();
    Part findPartById(Integer id);
    Part savePart(Part part);
    void deletePart(Part part);
}
