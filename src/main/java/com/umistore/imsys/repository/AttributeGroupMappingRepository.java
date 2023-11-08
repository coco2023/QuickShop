package com.umistore.imsys.repository;

import com.umistore.imsys.entity.AttributeGroupMapping;
import com.umistore.imsys.entity.AttributeGroupMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeGroupMappingRepository extends JpaRepository<AttributeGroupMapping, AttributeGroupMappingId> {

    // Find by AttributeGroupID
    List<AttributeGroupMapping> findByAttributeGroupId(int attributeGroupId);

    // Find by AttributeID
    List<AttributeGroupMapping> findByAttributeId(int attributeId);

//    // Delete by AttributeGroupMappingId
//    void deleteById(AttributeGroupMappingId id);
}
