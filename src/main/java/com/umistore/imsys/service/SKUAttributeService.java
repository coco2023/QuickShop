package com.umistore.imsys.service;

import com.umistore.imsys.entity.SKUAttribute;
import com.umistore.imsys.entity.SKUAttributeId;
import com.umistore.imsys.repository.SKUAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SKUAttributeService {

    @Autowired
    private SKUAttributeRepository skuAttributeRepository;

    public SKUAttribute createOrUpdateSKUAttribute(SKUAttribute skuAttribute) {
        return skuAttributeRepository.save(skuAttribute);
    }

    public List<SKUAttribute> findAll() {
        return skuAttributeRepository.findAll();
    }

    public void deleteSKUAttribute(SKUAttributeId id) {
        skuAttributeRepository.deleteById(id);
    }

    // Additional methods for search and other operations
    public List<SKUAttribute> findBySkuId(Integer skuId) {
        return skuAttributeRepository.findBySkuId(skuId);
    }

    public Optional<SKUAttribute> updateSKUAttribute(Integer skuId, Integer attributeValueId, SKUAttribute updatedSKUAttribute) {
        return skuAttributeRepository.findById(new SKUAttributeId(skuId, attributeValueId))
                .map(skuAttribute -> {
                    skuAttribute.setSkuId(updatedSKUAttribute.getSkuId());
                    skuAttribute.setAttributeValueId(updatedSKUAttribute.getAttributeValueId());
                    return skuAttributeRepository.save(skuAttribute);
                });
    }

}
