package com.umistore.imsys.controller;

import com.umistore.imsys.entity.Attribute;
import com.umistore.imsys.service.AttributeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attributes")
@Api(value = "AttributeController", description = "Operations for Attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute attribute) {
        Attribute createdAttribute = attributeService.createAttribute(attribute);
        return ResponseEntity.ok(createdAttribute);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attribute>> getAllAttributes() {
        List<Attribute> attributes = attributeService.getAllAttributes();
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable Integer id) {
        Optional<Attribute> attribute = attributeService.getAttributeById(id);
        return attribute.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Attribute>> searchAttributesByName(@RequestParam String name) {
        List<Attribute> attributes = attributeService.searchAttributesByName(name);
        return ResponseEntity.ok(attributes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attribute> updateAttribute(@PathVariable Integer id, @RequestBody Attribute attributeDetails) {
        Attribute updatedAttribute = attributeService.updateAttribute(id, attributeDetails);
        return ResponseEntity.ok(updatedAttribute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttribute(@PathVariable Integer id) {
        attributeService.deleteAttribute(id);
        return ResponseEntity.ok().build();
    }
}
