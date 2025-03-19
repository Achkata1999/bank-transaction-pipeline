# Bank Transaction Pipeline

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Fraud Detection Rules](#fraud-detection-rules)
4. [API Endpoints](#api-endpoints)
5. [Transaction Status Codes](#transaction-status-codes)
6. [Deployment Instructions](#deployment-instructions)
7. [Conclusion](#conclusion)

## Overview
The **Bank Transaction Pipeline** is a high-volume data pipeline system designed to process bank transactions in near real-time. It simulates a client application submitting transactions through a REST API. The system includes fraud detection capabilities and stores data in a MySQL database.

## Features
- **REST API** for submitting transactions.
- **Fraud Detection System** with multiple rules to detect unusual activity.
- **MySQL Database** for storing transaction data.
- **Docker Compose** support for easy deployment in containerized environments.

## Fraud Detection Rules
Transactions are flagged as fraudulent based on the following conditions:

1. **Excessive Transactions**: A user submits **more than 10 transactions within 1 minute**.
2. **Geographic Anomaly**: A user submits transactions from **locations more than 300 km apart within 30 minutes**.
3. **Blacklisted Country**: A transaction is created in a **blacklisted country**.
4. **Multi-Country Activity**: A user submits transactions in **3 different countries within 10 minutes**.

**Example of a geographic anomaly**: If a user initiates a transaction in New York and another one in Tokyo within 30 minutes, the system will flag this as potentially fraudulent.

## Postman Collection
A **Postman collection** is provided in the root directory of the repository to help you quickly test the API. You can import it into your Postman application and use the predefined requests to interact with the system.

## API Endpoints

### Submit a Transaction
- **Endpoint**: `POST /transaction/new`
- **Request Body**:
  ```json
  {
      "tran_id": "TXN0012",
      "user_id": "USER123",
      "amount": 1500.75,
      "timestamp": "2025-03-16T10:15:13.617Z",
      "country": "RU",
      "lat_coord": 40.7128,
      "long_coord": -14.0060
  }
  
- **Response Example (Fraud Detected - Blacklisted Country)**:
  ```json
  {
      "code": 3,
      "name": "Blacklisted Country",
      "message": "Transaction created within a blacklisted country"
  }
  
## Transaction Status Codes

| Code | Name                  | Description                                                              |
|------|-----------------------|--------------------------------------------------------------------------|
| 0    | Created               | Transaction is successfully created.                                      |
| 1    | Excessive Transactions | User has created more than 10 transactions within 1 minute.               |
| 2    | Geographic Anomaly    | Transactions from different locations more than 300 km apart within 30 minutes. |
| 3    | Blacklisted Country   | Transaction created within a blacklisted country.                         |
| 4    | Multi-Country Activity | User has created transactions in 3 different countries within 10 minutes. |

## Deployment Instructions

### Prerequisites
- Docker
- Docker Compose

### Steps to Deploy

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/bank-transaction-pipeline.git
   
2. Navigate to the project directory:
   ```sh
   cd bank-transaction-pipeline

3. Build and start the application using Docker Compose:
   ```sh
   docker compose up -d
   
## Conclusion
This project provides a scalable transaction processing system with built-in fraud detection rules. It can be deployed using Docker Compose, making it easy to spin up the entire system in a containerized environment.

For more details or to contribute, please refer to the source code or create an issue in the repository.

