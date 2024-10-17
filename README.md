# POS Backend

## Overview

The POS Backend project is a Spring Boot application designed to manage point-of-sale transactions. It provides functionalities for managing orders, customers, and items in a retail environment. The application exposes RESTful APIs to interact with the data, allowing for easy integration with front-end applications or other services.

## Features

- **Order Management**: Create, read, update, and delete orders.
- **Customer Management**: Handle customer data and relationships.
- **Item Management**: Manage inventory and item details.
- **Logging**: Detailed logging for tracking operations and errors.

## Technologies Used

- **Java**: Primary programming language.
- **Spring Boot**: Framework for building the RESTful API.
- **Hibernate/JPA**: ORM for database interactions.
- **MySQL**: Database for storing data.
- **Lombok**: Reduces boilerplate code with annotations.
- **Postman**: For testing the APIs.

## Getting Started

### Prerequisites

- Java JDK 17 or higher
- Maven 3.6 or higher
- MySQL server
- Postman or similar tool for API testing

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/KavinduSeneshSathsara/POS-System-Spring-Web-MVC.git
   cd POS-System-Spring-Web-MVC
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the API documentation via Postman or your browser at:
   ```
   http://localhost:8080/POS_Backend/api/v1/orders
   ```

## API Endpoints

### Orders

- **Create Order**
  - **Endpoint**: `POST /api/v1/orders`
  - **Request Body**:
    ```json
    {
     "customerId":"C0001",
     "orderDetails":[
			{
				"itemCode":"I0003",
				"qty":50,
				"unitPrice":100
            }
     ]
    }
- **Get All Orders**
  - **Endpoint**: `GET /api/v1/orders`

- **Get Order by ID**
  - **Endpoint**: `GET /api/v1/orders/{id}`

- **Update Order**
  - **Endpoint**: `PUT /api/v1/orders/{id}`
  - **Request Body**: Same structure as Create Order.

- **Delete Order**
  - **Endpoint**: `DELETE /api/v1/orders/{id}`

API Documentation

For detailed API documentation, please refer to the project’s Swagger UI and Postman collections.

API documentation URL: https://documenter.getpostman.com/view/35384474/2sAXxV6VZX

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


© All rights reserved. This project is created and maintained by Kavindu Senesh.

