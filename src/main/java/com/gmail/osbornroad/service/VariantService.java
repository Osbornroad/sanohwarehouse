package com.gmail.osbornroad.service;

import com.gmail.osbornroad.model.jpa.Variant;
import com.gmail.osbornroad.repository.jpa.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VariantService {

    @Autowired
    VariantRepository variantRepository;

    @Transactional(readOnly = true)
    public List<Variant> findAllVariants() {
        List<Variant> variantList = new ArrayList<>();
        Iterable<Variant> iterable = variantRepository.findAll();
        iterable.forEach(variantList::add);
        return variantList;
    }

    @Transactional(readOnly = true)
    public Variant findVariantById(Integer id) {
        Optional<Variant> optional = variantRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Variant saveVariant(Variant variant) {
        return variantRepository.save(variant);
    }

    public void deleteVariant(Variant variant) {
        variantRepository.delete(variant);
    }
}
