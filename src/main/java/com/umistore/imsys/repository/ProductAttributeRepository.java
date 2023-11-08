package com.umistore.imsys.repository;

import com.umistore.imsys.entity.ProductAttribute;
import com.umistore.imsys.entity.ProductAttributeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, ProductAttributeId> {
    // Additional methods can be declared here
}
