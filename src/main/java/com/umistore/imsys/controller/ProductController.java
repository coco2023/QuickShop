package com.umistore.imsys.controller;

import com.umistore.imsys.entity.Product;
import com.umistore.imsys.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(value = "ProductController", description = "Operations pertaining to products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    @ApiOperation(value = "create Product", notes = "create Product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete Product", notes = "delete Product")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update Product", notes = "update Product name, description, price")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // Search for products by name
    @GetMapping("/search")
    @ApiOperation(value = "search Product", notes = "Search for products by name")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all Product", notes = "get all Product")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
