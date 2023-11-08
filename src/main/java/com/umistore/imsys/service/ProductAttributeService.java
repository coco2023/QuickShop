package com.umistore.imsys.service;

import com.umistore.imsys.entity.ProductAttribute;
import com.umistore.imsys.entity.ProductAttributeId;
import com.umistore.imsys.repository.ProductAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductAttributeService {

    @Autowired
    private ProductAttributeRepository productAttributeRepository;

    public ProductAttribute createOrUpdateProductAttribute(ProductAttribute productAttribute) {
        return productAttributeRepository.save(productAttribute);
    }

    public Optional<ProductAttribute> findById(ProductAttributeId id) {
        return productAttributeRepository.findById(id);
    }

    public List<ProductAttribute> findAll() {
        return productAttributeRepository.findAll();
    }

    public void deleteProductAttribute(ProductAttributeId id) {
        productAttributeRepository.deleteById(id);
    }

}
