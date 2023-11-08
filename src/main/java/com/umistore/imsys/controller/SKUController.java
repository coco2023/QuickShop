package com.umistore.imsys.controller;

import com.umistore.imsys.entity.SKU;
import com.umistore.imsys.service.SKUService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skus")
@Api(value = "SKUController", description = "Operations for SKU")
public class SKUController {

    @Autowired
    private SKUService skuService;

    @PostMapping
    public ResponseEntity<SKU> createSKU(@RequestBody SKU sku) {
        SKU newSKU = skuService.createOrUpdateSKU(sku);
        return ResponseEntity.ok(newSKU);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SKU>> getAllSKUs() {
        List<SKU> list = skuService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SKU> getSKUById(@PathVariable Integer id) {
        return skuService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSKU(@PathVariable Integer id) {
        skuService.deleteSKU(id);
        return ResponseEntity.ok().build();
    }

    // Endpoints for update and search
    @GetMapping("/search")
    public ResponseEntity<SKU> getSKUBySku(@RequestParam String sku) {
        SKU foundSKU = skuService.findBySku(sku);
        if (foundSKU != null) {
            return ResponseEntity.ok(foundSKU);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SKU> updateSKU(@PathVariable Integer id, @RequestBody SKU skuDetails) {
        SKU updatedSKU = skuService.updateSKU(id, skuDetails);
        return ResponseEntity.ok(updatedSKU);
    }

}
