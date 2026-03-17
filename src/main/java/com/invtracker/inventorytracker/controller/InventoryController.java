package com.invtracker.inventorytracker.controller;

import com.invtracker.inventorytracker.model.InventorySummary;
import com.invtracker.inventorytracker.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/analyze-inventory")
    public ResponseEntity<?> analyzeInventory(
            @RequestParam("file") MultipartFile file) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("CSV file must not be empty");
            }

            InventorySummary result = inventoryService.analyzeInventory(file);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing CSV: " + e.getMessage());
        }
    }
}