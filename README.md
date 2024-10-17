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
   git clone https://github.com/yourusername/POS-Backend.git
   cd POS-Backend
   ```

2. Update the `application.properties` file with your MySQL database connection details:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the API documentation via Postman or your browser at:
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
      "customerId": "C0001",
      "items": [
        {
          "itemId": "I0002",
          "quantity": 540
        },
        {
          "itemId": "I0002",
          "quantity": 50
        }
      ]
    }
    ```

- **Get All Orders**
  - **Endpoint**: `GET /api/v1/orders`

- **Get Order by ID**
  - **Endpoint**: `GET /api/v1/orders/{id}`

- **Update Order**
  - **Endpoint**: `PUT /api/v1/orders/{id}`
  - **Request Body**: Same structure as Create Order.

- **Delete Order**
  - **Endpoint**: `DELETE /api/v1/orders/{id}`

## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/YourFeature`.
3. Make your changes and commit them: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature/YourFeature`.
5. Open a Pull Request.

API Documentation

For detailed API documentation, please refer to the project’s Swagger UI and Postman collections.

API documentation URL: https://documenter.getpostman.com/view/35384474/2sAXxV6VZX

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```
