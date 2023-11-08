package com.umistore.imsys.service;

import com.umistore.imsys.entity.Reconciliation;
import com.umistore.imsys.repository.ReconciliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ReconciliationService {
    @Autowired
    private ReconciliationRepository reconciliationRepository;

    @Transactional
    public Reconciliation createReconciliation(Reconciliation reconciliation) {
        return reconciliationRepository.save(reconciliation);
    }

    public void deleteReconciliation(Integer id) {
        reconciliationRepository.deleteById(id);
    }

    public Reconciliation updateReconciliation(Integer reconciliationId, Reconciliation newReconciliationData) {
        return reconciliationRepository.findById(reconciliationId)
                .map(reconciliation -> {
                    reconciliation.setRecordedQuantity(newReconciliationData.getRecordedQuantity());
                    reconciliation.setObservedQuantity(newReconciliationData.getObservedQuantity());
                    reconciliation.setNotes(newReconciliationData.getNotes());
                    // Add more fields to update as necessary
                    return reconciliationRepository.save(reconciliation);
                }).orElseThrow(() -> new EntityNotFoundException("Reconciliation not found with id " + reconciliationId));
    }

    public List<Reconciliation> searchByInventory(Integer inventoryId) {
        return reconciliationRepository.findByInventoryId(inventoryId);
    }

    public List<Reconciliation> getAllReconciliations() {
        return reconciliationRepository.findAll();
    }

}
