package com.umistore.imsys.repository;

import com.umistore.imsys.entity.Reconciliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReconciliationRepository extends JpaRepository<Reconciliation, Integer> {

    List<Reconciliation> findByInventoryId(Integer inventoryId);

}
