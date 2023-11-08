package com.umistore.imsys.repository;

import com.umistore.imsys.entity.AttributeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeGroupRepository extends JpaRepository<AttributeGroup, Integer> {
    List<AttributeGroup> findByGroupName(String groupName);
}
