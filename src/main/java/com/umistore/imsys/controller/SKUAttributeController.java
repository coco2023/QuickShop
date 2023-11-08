package com.umistore.imsys.controller;

import com.umistore.imsys.entity.SKUAttribute;
import com.umistore.imsys.entity.SKUAttributeId;
import com.umistore.imsys.service.SKUAttributeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skuattributes")
@Api(value = "SKUAttributeController", description = "Operations for SKUAttribute")
public class SKUAttributeController {

    @Autowired
    private SKUAttributeService skuAttributeService;

    @PostMapping
    public ResponseEntity<SKUAttribute> createSKUAttribute(@RequestBody SKUAttribute skuAttribute) {
        SKUAttribute newSKUAttribute = skuAttributeService.createOrUpdateSKUAttribute(skuAttribute);
        return ResponseEntity.ok(newSKUAttribute);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SKUAttribute>> getAllSKUAttributes() {
        List<SKUAttribute> list = skuAttributeService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSKUAttribute(@RequestBody SKUAttributeId id) {
        skuAttributeService.deleteSKUAttribute(id);
        return ResponseEntity.ok().build();
    }

    // Endpoints for update and search
    @GetMapping("/search")
    public ResponseEntity<List<SKUAttribute>> findSKUAttributesBySKUId(@RequestParam Integer skuId) {
        List<SKUAttribute> skuAttributes = skuAttributeService.findBySkuId(skuId);
        return ResponseEntity.ok(skuAttributes);
    }

    @PutMapping("/{skuId}/{attributeValueId}")
    public ResponseEntity<SKUAttribute> updateSKUAttribute(@PathVariable Integer skuId,
                                                           @PathVariable Integer attributeValueId,
                                                           @RequestBody SKUAttribute skuAttribute) {
        Optional<SKUAttribute> updatedSKUAttribute = skuAttributeService.updateSKUAttribute(skuId, attributeValueId, skuAttribute);
        return updatedSKUAttribute
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
