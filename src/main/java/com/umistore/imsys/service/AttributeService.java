package com.umistore.imsys.service;

import com.umistore.imsys.entity.Attribute;
import com.umistore.imsys.exception.ResourceNotFoundException;
import com.umistore.imsys.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    public Attribute createAttribute(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    public Optional<Attribute> getAttributeById(Integer id) {
        return attributeRepository.findById(id);
    }

    public List<Attribute> searchAttributesByName(String name) {
        return attributeRepository.findByNameContaining(name);
    }

    public Attribute updateAttribute(Integer id, Attribute attributeDetails) {
        return attributeRepository.findById(id).map(attribute -> {
            attribute.setName(attributeDetails.getName());
            // Other fields can be updated as needed
            return attributeRepository.save(attribute);
        }).orElseThrow(() -> new ResourceNotFoundException("Attribute not found with id " + id));
    }

    public void deleteAttribute(Integer id) {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute not found with id " + id));
        attributeRepository.delete(attribute);
    }

    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }
}
