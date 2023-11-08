package com.umistore.imsys.controller;

import com.umistore.imsys.entity.AttributeGroupMapping;
import com.umistore.imsys.entity.AttributeGroupMappingId;
import com.umistore.imsys.service.AttributeGroupMappingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attributeGroupMappings")
@Api(value = "AttributeGroupMappingController", description = "Operations for AttributeGroupMapping")
public class AttributeGroupMappingController {

    @Autowired
    private AttributeGroupMappingService attributeGroupMappingService;

    @PostMapping
    public ResponseEntity<AttributeGroupMapping> createAttributeGroupMapping(@RequestBody AttributeGroupMapping attributeGroupMapping) {
        AttributeGroupMapping createdAttributeGroupMapping = attributeGroupMappingService.createAttributeGroupMapping(attributeGroupMapping);
        return ResponseEntity.ok(createdAttributeGroupMapping);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttributeGroupMapping>> getAllAttributeGroupMappings() {
        List<AttributeGroupMapping> attributeGroupMappings = attributeGroupMappingService.getAllAttributeGroupMappings();
        return ResponseEntity.ok(attributeGroupMappings);
    }

    @GetMapping("/byAttributeGroup/{attributeGroupId}")
    public ResponseEntity<List<AttributeGroupMapping>> findByAttributeGroup(@PathVariable int attributeGroupId) {
        List<AttributeGroupMapping> attributeGroupMappings = attributeGroupMappingService.findByAttributeGroup(attributeGroupId);
        return ResponseEntity.ok(attributeGroupMappings);
    }

    @GetMapping("/byAttribute/{attributeId}")
    public ResponseEntity<List<AttributeGroupMapping>> findByAttribute(@PathVariable int attributeId) {
        List<AttributeGroupMapping> attributeGroupMappings = attributeGroupMappingService.findByAttribute(attributeId);
        return ResponseEntity.ok(attributeGroupMappings);
    }

    @DeleteMapping("/{attributeGroupId}/{attributeId}")
    public ResponseEntity<Void> deleteAttributeGroupMapping(
            @PathVariable int attributeGroupId,
            @PathVariable int attributeId) {
        attributeGroupMappingService.deleteAttributeGroupMapping(new AttributeGroupMappingId(attributeGroupId, attributeId));
        return ResponseEntity.noContent().build();
    }
}
