# E-Commerce Application

A backend-focused **E-Commerce Application** built using **Java and Spring Boot**.
The project follows a layered architecture and provides REST APIs for managing users, products, carts, and orders.

## 🚀 Features

* User management
* Product management
* Cart management
* Order management
* RESTful APIs
* MySQL database integration
* Input validation
* Exception handling
* Layered architecture
* JPA/Hibernate for database operations

## 🛠️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **MySQL**
* **Maven**
* **REST APIs**
* **Postman**
* **Git & GitHub**

## 📂 Project Structure

```text
src
└── main
    └── java
        └── ecom_application
            ├── Controller
            ├── Service
            ├── Repository
            ├── Model
            ├── DTO
            └── Exception
```

### Architecture

The application follows a basic layered architecture:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
MySQL Database
```

* **Controller** – Handles HTTP requests and responses.
* **Service** – Contains application/business logic.
* **Repository** – Communicates with the database using Spring Data JPA.
* **Model/Entity** – Represents database tables.
* **DTO** – Used for transferring request and response data.

## 🔗 API Modules

### User APIs

Used for creating and managing users.

Example:

```http
POST /api/users
GET /api/users/{id}
```

### Product APIs

Used for adding, retrieving, updating and deleting products.

Example:

```http
POST /api/products
GET /api/products
GET /api/products/{id}
PUT /api/products/{id}
DELETE /api/products/{id}
```

### Cart APIs

Used to add products to a cart, update cart items and retrieve cart details.

Example:

```http
POST /api/cart
GET /api/cart/{userId}
```

### Order APIs

Used for creating and managing customer orders.

Example:

```http
POST /api/orders
GET /api/orders/{id}
```

> API endpoints may vary depending on the current implementation.

## 🗄️ Database

The application uses **MySQL** as the relational database.

JPA/Hibernate is used to map Java entities to database tables and perform CRUD operations.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace the database credentials with your own values.

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

### 2. Open the project

Open the project in **IntelliJ IDEA**, **Eclipse**, or another Java IDE.

### 3. Configure MySQL

Create a database:

```sql
CREATE DATABASE ecommerce;
```

Update the database credentials in:

```text
src/main/resources/application.properties
```

### 4. Build the project

Using Maven:

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class directly from your IDE.

The application will start on:

```text
http://localhost:8080
```

## 🧪 Testing APIs

The APIs can be tested using **Postman**.

You can test:

* User creation and retrieval
* Product CRUD operations
* Adding products to cart
* Updating cart items
* Creating orders
* Retrieving order details

## 📌 What I Learned

Through this project, I worked with:

* Building REST APIs using Spring Boot
* Designing a layered backend architecture
* Implementing CRUD operations
* Database relationships using JPA/Hibernate
* DTO-based request/response handling
* Exception handling and validation
* Testing APIs using Postman
* Managing a backend project using Git and GitHub

## 🔮 Future Improvements

* JWT-based authentication and authorization
* Role-based access control
* Payment gateway integration
* Product search and filtering
* Pagination and sorting
* Redis caching
* Docker deployment
* Order tracking
* API documentation using Swagger/OpenAPI

## 👨‍💻 Author

**Owais Ahmad**

B.Tech Information Technology
CUSAT

GitHub: https://github.com/Owaisahmad786425

LinkedIn: https://www.linkedin.com/in/owais-ahmad-6359142a1/
