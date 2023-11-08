package com.umistore.imsys.repository;

import com.umistore.imsys.entity.SKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKURepository extends JpaRepository<SKU, Integer> {

    // search sku by skuName
    SKU findBySku(String skuName);

}
