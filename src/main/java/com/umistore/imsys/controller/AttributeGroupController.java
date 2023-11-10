package com.umistore.imsys.controller;

import com.umistore.imsys.entity.AttributeGroup;
import com.umistore.imsys.service.AttributeGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributeGroups")
@Api(value = "AttributeGroupController", description = "Operations for AttributeGroup")
public class AttributeGroupController {

    @Autowired
    private AttributeGroupService attributeGroupService;

    @PostMapping
    public ResponseEntity<AttributeGroup> createAttributeGroup(@RequestBody AttributeGroup attributeGroup) {
        AttributeGroup createdAttributeGroup = attributeGroupService.createAttributeGroup(attributeGroup);
        return ResponseEntity.ok(createdAttributeGroup);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttributeGroup>> getAllAttributeGroups() {
        List<AttributeGroup> attributeGroups = attributeGroupService.getAllAttributeGroups();
        return ResponseEntity.ok(attributeGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeGroup> getAttributeGroupById(@PathVariable Integer id) {
        AttributeGroup attributeGroup = attributeGroupService.getAttributeGroupById(id);
        return ResponseEntity.ok(attributeGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeGroup> updateAttributeGroup(@PathVariable Integer id, @RequestBody AttributeGroup attributeGroupDetails) {
        AttributeGroup updatedAttributeGroup = attributeGroupService.updateAttributeGroup(id, attributeGroupDetails);
        return ResponseEntity.ok(updatedAttributeGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttributeGroup(@PathVariable Integer id) {
        attributeGroupService.deleteAttributeGroup(id);
        return ResponseEntity.noContent().build();
    }

    // Search for AttributeGroups by GroupName
    @GetMapping("/search")
    public ResponseEntity<List<AttributeGroup>> searchAttributeGroupsByGroupName(
            @RequestParam String groupName) {
        List<AttributeGroup> attributeGroups = attributeGroupService.findByGroupName(groupName);
        return ResponseEntity.ok(attributeGroups);
    }

}
