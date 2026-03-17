package com.invtracker.inventorytracker.service.impl;

import com.invtracker.inventorytracker.model.Anomaly;
import com.invtracker.inventorytracker.model.InventoryItem;
import com.invtracker.inventorytracker.model.InventorySummary;
import com.invtracker.inventorytracker.parser.CsvParser;
import com.invtracker.inventorytracker.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class InventoryServiceImpl implements InventoryService {

    private static final int LOW_STOCK_THRESHOLD = 10;
    private static final List<String> VALID_TYPES = List.of("in", "out");


    @Override
    public InventorySummary analyzeInventory(MultipartFile file) throws Exception {

        List<Map<String, String>> records = CsvParser.parse(file.getInputStream());


        Map<String, Integer> stockMap = new HashMap<>();
        Map<String, String> productNames = new HashMap<>();

        List<Anomaly> anomalies = new ArrayList<>();


        int rowNumber = 1;


        for(Map<String, String> record : records) {
            rowNumber++;


            try {
                String productId = record.get("product_id");
                String productName = record.get("product_name");
                String type = record.get("type");
                String quantityStr = record.get("quantity");



                if (productId == null || productName == null || type == null || quantityStr == null) {
                    anomalies.add(new Anomaly(rowNumber, "Missing required fields"));
                    continue;
                }

                int quantity = Integer.parseInt(quantityStr);

                //negative inventory detection
                if(quantity < 0) {
                    anomalies.add(new Anomaly(rowNumber ,"Negative stock detected: " + quantity));
                }

                //invalid type detection
                if(!VALID_TYPES.contains(type)) {
                    anomalies.add(new Anomaly(rowNumber, "Invalid type: " + type));
                }


                // inventory calculation
                productNames.put(productId, productName);
                stockMap.putIfAbsent(productId, 0);
                int current = stockMap.get(productId);

                if(type.equals("in")) {
                    stockMap.put(productId, current += quantity);
                } else if(type.equals("out")) {
                    stockMap.put(productId, current -= quantity);
                }


            } catch(Exception e) {
                anomalies.add(new Anomaly(rowNumber, "Error parsing row: " + e.getMessage()));
            }

        }



        List<InventoryItem> inventory = new ArrayList<>();
        List<InventoryItem> lowStock = new ArrayList<>();





        for (String productId : stockMap.keySet()) {

            int stock = stockMap.get(productId);

            InventoryItem item = new InventoryItem(
                    productId,
                    productNames.get(productId),
                    stock
            );

            inventory.add(item);

            if (stock < LOW_STOCK_THRESHOLD) {
                lowStock.add(item);
            }
        }

        return new InventorySummary(inventory, lowStock, anomalies);
    }
}