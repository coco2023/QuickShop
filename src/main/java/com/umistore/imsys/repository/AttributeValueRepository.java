package com.umistore.imsys.repository;

import com.umistore.imsys.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {

    List<AttributeValue> findByAttributeID(Integer attributeID);

//    List<AttributeValue> findByAttributeId(Integer attributeId);
}
