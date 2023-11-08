package com.umistore.imsys.service;

import com.umistore.imsys.entity.AttributeValue;
import com.umistore.imsys.exception.ResourceNotFoundException;
import com.umistore.imsys.repository.AttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AttributeValueService {

    @Autowired
    private AttributeValueRepository attributeValueRepository;

    public AttributeValue createAttributeValue(AttributeValue attributeValue) {
        return attributeValueRepository.save(attributeValue);
    }

    public Optional<AttributeValue> getAttributeValueById(Integer id) {
        return attributeValueRepository.findById(id);
    }

    public List<AttributeValue> searchByAttributeID(Integer attributeID) {
        return attributeValueRepository.findByAttributeID(attributeID);
    }

    public AttributeValue updateAttributeValue(Integer id, AttributeValue updatedAttributeValue) {
        return attributeValueRepository.findById(id)
                .map(attributeValue -> {
                    attributeValue.setValue(updatedAttributeValue.getValue());
                    // You can add more fields to update here
                    return attributeValueRepository.save(attributeValue);
                })
                .orElseGet(() -> {
                    updatedAttributeValue.setAttributeValueID(id);
                    return attributeValueRepository.save(updatedAttributeValue);
                });
    }

    public void deleteAttributeValue(Integer id) {
        AttributeValue attributeValue = attributeValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AttributeValue not found with id " + id));
        attributeValueRepository.delete(attributeValue);
    }

    public List<AttributeValue> findAll() {
        return attributeValueRepository.findAll();
    }
}
