package com.umistore.imsys.controller;

import com.umistore.imsys.entity.Reconciliation;
import com.umistore.imsys.service.ReconciliationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reconciliation")
@Api(value = "ReconciliationController", description = "Operations for reconciliation")
public class ReconciliationController {

    @Autowired
    private ReconciliationService reconciliationService;

    @PostMapping("/")
    public ResponseEntity<Reconciliation> createReconciliation(@RequestBody Reconciliation reconciliation) {
        Reconciliation savedReconciliation = reconciliationService.createReconciliation(reconciliation);
        return ResponseEntity.ok(savedReconciliation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReconciliation(@PathVariable Integer id) {
        reconciliationService.deleteReconciliation(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reconciliation> updateReconciliation(@PathVariable Integer id, @RequestBody Reconciliation reconciliation) {
        return ResponseEntity.ok(reconciliationService.updateReconciliation(id, reconciliation));
    }

    // Search for reconciliations by inventory ID
    @GetMapping("/search")
    public ResponseEntity<List<Reconciliation>> searchReconciliationsByInventory(@RequestParam Integer inventoryId) {
        List<Reconciliation> reconciliations = reconciliationService.searchByInventory(inventoryId);
        return new ResponseEntity<>(reconciliations, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reconciliation>> getAllReconciliations() {
        List<Reconciliation> reconciliations = reconciliationService.getAllReconciliations();
        return new ResponseEntity<>(reconciliations, HttpStatus.OK);
    }

}
