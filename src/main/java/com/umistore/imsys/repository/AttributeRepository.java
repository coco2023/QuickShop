package com.umistore.imsys.repository;

import com.umistore.imsys.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
    List<Attribute> findByNameContaining(String name);
}
