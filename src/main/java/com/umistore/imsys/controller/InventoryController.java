package com.umistore.imsys.controller;

import com.umistore.imsys.entity.Inventory;
import com.umistore.imsys.service.InventoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Api(value = "InventoryController", description = "Operations for inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.ok(savedInventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventory));
    }

    // Search for inventory by product ID
    @GetMapping("/search")
    public ResponseEntity<Inventory> searchInventoryByProduct(@RequestParam Integer productId) {
        Inventory inventory = inventoryService.searchByProduct(productId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllInventoryItems() {
        List<Inventory> inventoryList = inventoryService.getAllInventoryItems();
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

}
