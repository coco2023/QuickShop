package com.umistore.imsys.repository;

import com.umistore.imsys.entity.SKUAttribute;
import com.umistore.imsys.entity.SKUAttributeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SKUAttributeRepository extends JpaRepository<SKUAttribute, SKUAttributeId> {
    List<SKUAttribute> findBySkuId(Integer skuId);
    // Custom query methods can be added here
}
