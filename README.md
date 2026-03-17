# Warehouse Inventory Analyzer API (Java Challenge)

## Overview

In this exercise, I will build a **Java API service** that processes **warehouse inventory movement logs** and returns the **current state of stock**.

The service should accept a **CSV file** containing stock movements and return a summary including:

* Current inventory levels
* Low stock alerts
* Any detected anomalies in the data

The focus of this challenge is on **clean backend design, data processing, and robust error handling** using Java.

_**You can copy my design and apply on your own language or framework if you prefer, but I will be implementing it in Java with Spring Boot for this challenge.**_

---


# Implement a REST API endpoint:



```
POST /analyze-inventory
```

The endpoint must:

* Accept a **CSV file upload**
* Process the inventory movements
* Return a **JSON summary of inventory status**


---

# Example CSV
(You can just create a .csv file copying the content below for testing):

```csv
timestamp,product_id,product_name,type,quantity
1717243200,1001,Wireless Mouse,in,50
1717246800,1002,Mechanical Keyboard,in,30
1717250400,1001,Wireless Mouse,out,5
1717254000,1003,USB-C Cable,in,100
1717257600,1002,Mechanical Keyboard,out,10
1717261200,1001,Wireless Mouse,out,10
1717264800,1003,USB-C Cable,out,25
```

---

# Processing Rules

## 1. Calculate Current Inventory

For each product:

```
current_stock = total_in - total_out
```

---

## 2. Generate Low Stock Alerts

If the stock level falls below a defined threshold (for example **10 units**), it should appear in a `low_stock` list.

Example:

```json
"low_stock": [
  {
    "product_id": "1001",
    "product_name": "Wireless Mouse",
    "current_stock": 8
  }
]
```

---

## 3. Detect Anomalies

The system should detect possible issues such as:

* Negative inventory
* Invalid movement type
* Non-positive quantities
* Missing required fields
* Malformed CSV rows

Example:

```json
"anomalies": [
  {
    "row": 12,
    "error": "Negative stock detected"
  }
]
```

---

# Example Response

```json
{
  "inventory": [
    {
      "product_id": "1001",
      "product_name": "Wireless Mouse",
      "current_stock": 35
    },
    {
      "product_id": "1002",
      "product_name": "Mechanical Keyboard",
      "current_stock": 20
    },
    {
      "product_id": "1003",
      "product_name": "USB-C Cable",
      "current_stock": 75
    }
  ],
  "low_stock": [],
  "anomalies": []
}
```

---

# Tech stack

I use:

* **Java 17+**
* **Spring Boot**
* **Maven or Gradle**
* **OpenCSV / Apache Commons CSV** for CSV parsing

---

# Example Request

Using `curl`:

```
curl -X POST http://localhost:8080/analyze-inventory \
  -F "file=@inventory.csv"
```