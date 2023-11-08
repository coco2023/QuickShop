package com.umistore.imsys.service;

import com.umistore.imsys.entity.AttributeGroupMapping;
import com.umistore.imsys.entity.AttributeGroupMappingId;
import com.umistore.imsys.repository.AttributeGroupMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttributeGroupMappingService {

    @Autowired
    private AttributeGroupMappingRepository attributeGroupMappingRepository;

    public AttributeGroupMapping createAttributeGroupMapping(AttributeGroupMapping attributeGroupMapping) {
        return attributeGroupMappingRepository.save(attributeGroupMapping);
    }

    public List<AttributeGroupMapping> getAllAttributeGroupMappings() {
        return attributeGroupMappingRepository.findAll();
    }

    public List<AttributeGroupMapping> findByAttributeGroup(int attributeGroupId) {
        return attributeGroupMappingRepository.findByAttributeId(attributeGroupId);
    }

    public List<AttributeGroupMapping> findByAttribute(int attributeId) {
        return attributeGroupMappingRepository.findByAttributeId(attributeId);
    }

    public void deleteAttributeGroupMapping(AttributeGroupMappingId id) {
        attributeGroupMappingRepository.deleteById(id);
    }
}
