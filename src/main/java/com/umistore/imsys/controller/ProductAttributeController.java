package com.umistore.imsys.controller;

import com.umistore.imsys.entity.ProductAttribute;
import com.umistore.imsys.entity.ProductAttributeId;
import com.umistore.imsys.service.ProductAttributeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productAttributes")
@Api(value = "ProductAttributeController", description = "Operations for productAttributes")
public class ProductAttributeController {

    @Autowired
    private ProductAttributeService productAttributeService;

    @PostMapping
    public ResponseEntity<ProductAttribute> createProductAttribute(@RequestBody ProductAttribute productAttribute) {
        ProductAttribute newProductAttribute = productAttributeService.createOrUpdateProductAttribute(productAttribute);
        return ResponseEntity.ok(newProductAttribute);
    }

    @GetMapping("/{productId}/{attributeValueId}")
    public ResponseEntity<ProductAttribute> getProductAttributeById(@PathVariable Integer productId, @PathVariable Integer attributeValueId) {
        return productAttributeService.findById(new ProductAttributeId(productId, attributeValueId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductAttribute>> getAllProductAttributes() {
        List<ProductAttribute> list = productAttributeService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{productId}/{attributeValueId}")
    public ResponseEntity<Void> deleteProductAttribute(@PathVariable Integer productId, @PathVariable Integer attributeValueId) {
        productAttributeService.deleteProductAttribute(new ProductAttributeId(productId, attributeValueId));
        return ResponseEntity.ok().build();
    }

}
