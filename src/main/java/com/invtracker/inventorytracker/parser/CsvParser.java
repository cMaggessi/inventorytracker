package com.invtracker.inventorytracker.parser;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {

    public static List<Map<String, String>> parse(InputStream inputStream) throws Exception {

        List<Map<String, String>> records = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {

            String[] headers = reader.readNext();

            if (headers == null) {
                throw new RuntimeException("CSV file is empty");
            }

            String[] row;

            while ((row = reader.readNext()) != null) {

                Map<String, String> record = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {

                    String value = i < row.length ? row[i] : "";

                    record.put(headers[i], value);
                }

                records.add(record);
            }
        }

        return records;
    }
}