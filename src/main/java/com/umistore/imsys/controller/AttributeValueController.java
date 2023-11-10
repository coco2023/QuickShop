package com.umistore.imsys.controller;

import com.umistore.imsys.entity.AttributeValue;
import com.umistore.imsys.service.AttributeValueService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attributeValues")
@Api(value = "AttributeValueController", description = "Operations for AttributeValue")
public class AttributeValueController {

    @Autowired
    private AttributeValueService attributeValueService;

    @PostMapping
    public ResponseEntity<AttributeValue> createAttributeValue(@RequestBody AttributeValue attributeValue) {
        AttributeValue createdAttributeValue = attributeValueService.createAttributeValue(attributeValue);
        return ResponseEntity.ok(createdAttributeValue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeValue> getAttributeValueById(@PathVariable Integer id) {
        Optional<AttributeValue> attributeValue = attributeValueService.getAttributeValueById(id);
        return attributeValue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttributeValue>> getAlAttributeValue() {
        List<AttributeValue> attributeValueList = attributeValueService.findAll();
        return ResponseEntity.ok(attributeValueList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttributeValue(@PathVariable Integer id) {
        attributeValueService.deleteAttributeValue(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AttributeValue>> searchByAttributeID(@RequestParam Integer attributeID) {
        List<AttributeValue> attributeValues = attributeValueService.searchByAttributeID(attributeID);
        return ResponseEntity.ok(attributeValues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeValue> updateAttributeValue(@PathVariable Integer id, @RequestBody AttributeValue attributeValue) {
        return ResponseEntity.ok(attributeValueService.updateAttributeValue(id, attributeValue));
    }

}
