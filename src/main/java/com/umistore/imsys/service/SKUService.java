package com.umistore.imsys.service;

import com.umistore.imsys.entity.SKU;
import com.umistore.imsys.repository.SKURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SKUService {

    @Autowired
    private SKURepository skuRepository;

    public SKU createOrUpdateSKU(SKU sku) {
        return skuRepository.save(sku);
    }

    public Optional<SKU> findById(Integer id) {
        return skuRepository.findById(id);
    }

    public List<SKU> findAll() {
        return skuRepository.findAll();
    }

    public void deleteSKU(Integer id) {
        skuRepository.deleteById(id);
    }

    // Additional methods for search and other operations
    public SKU findBySku(String sku) {
        return skuRepository.findBySku(sku);
    }

    public SKU updateSKU(Integer id, SKU skuDetails) {
        return skuRepository.findById(id).map(sku -> {
            sku.setSku(skuDetails.getSku());
            // Other fields to be updated can be set here
            return skuRepository.save(sku);
        }).orElseThrow(() -> new RuntimeException("SKU not found with id " + id));
    }

}
