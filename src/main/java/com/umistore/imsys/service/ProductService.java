package com.umistore.imsys.service;

import com.umistore.imsys.entity.Product;
import com.umistore.imsys.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Log4j2
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Integer productId, Product newProductData) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(newProductData.getName());
                    product.setDescription(newProductData.getDescription());
                    product.setPrice(newProductData.getPrice());
                    // Add more fields to update as necessary
                    return productRepository.save(product);
                }).orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        log.info("productList: " + productList);
        return productList;
    }

}
