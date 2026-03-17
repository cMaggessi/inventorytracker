package com.invtracker.inventorytracker.service;

import com.invtracker.inventorytracker.model.InventorySummary;
import org.springframework.web.multipart.MultipartFile;

public interface InventoryService {
        InventorySummary analyzeInventory(MultipartFile file) throws Exception;
}
