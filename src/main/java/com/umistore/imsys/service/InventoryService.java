package com.umistore.imsys.service;

import com.umistore.imsys.entity.Inventory;
import com.umistore.imsys.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory updateInventory(Integer inventoryId, Inventory newInventoryData) {
        return inventoryRepository.findById(inventoryId)
                .map(inventory -> {
                    inventory.setQuantityAvailable(newInventoryData.getQuantityAvailable());
                    // Add more fields to update as necessary
                    return inventoryRepository.save(inventory);
                }).orElseThrow(() -> new EntityNotFoundException("Inventory not found with id " + inventoryId));
    }

    public Inventory searchByProduct(Integer productId) {
        return inventoryRepository.findByProductId(productId).orElse(null);
    }

    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

}
