package com.umistore.imsys.service;

import com.umistore.imsys.entity.AttributeGroup;
import com.umistore.imsys.exception.ResourceNotFoundException;
import com.umistore.imsys.repository.AttributeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeGroupService {

    @Autowired
    private AttributeGroupRepository attributeGroupRepository;

    public AttributeGroup createAttributeGroup(AttributeGroup attributeGroup) {
        return attributeGroupRepository.save(attributeGroup);
    }

    public AttributeGroup getAttributeGroupById(Integer id) throws ResourceNotFoundException {
        return attributeGroupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AttributeGroup not found for this id :: " + id));
    }

    public AttributeGroup updateAttributeGroup(Integer id, AttributeGroup attributeGroupDetails) {
        AttributeGroup attributeGroup = getAttributeGroupById(id);
        attributeGroup.setGroupName(attributeGroupDetails.getGroupName());
        // Other fields can be updated as needed
        return attributeGroupRepository.save(attributeGroup);
    }

    public void deleteAttributeGroup(Integer id) {
        AttributeGroup attributeGroup = getAttributeGroupById(id);
        attributeGroupRepository.delete(attributeGroup);
    }

    public List<AttributeGroup> findByGroupName(String groupName) {
        return attributeGroupRepository.findByGroupName(groupName);
    }

    public List<AttributeGroup> getAllAttributeGroups() {
        return attributeGroupRepository.findAll();
    }
}
