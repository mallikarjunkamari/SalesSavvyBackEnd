# SalesSavvy – E-commerce Backend

SalesSavvy Backend is a **Spring Boot REST API** that powers the SalesSavvy e-commerce platform.  
It handles product management, user management, and cart operations, and provides APIs for the React frontend.

---

## 🚀 Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST APIs

---

## ✨ Features

- User management
- Product management
- Add products to cart
- Update cart items
- Remove products from cart
- RESTful API integration
- Database persistence using MySQL

---

## 📁 Project Structure

```
SalesSavvyBackEnd
│
├── src/main/java
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── SalesSavvyApplication.java
│
├── src/main/resources
│   └── application.properties
│
├── pom.xml
└── README.md
```

---

## ⚙️ Installation and Setup

### 1. Clone the repository

```
git clone https://github.com/mallikarjunkamari/SalesSavvyBackEnd.git
```

### 2. Navigate to the project directory

```
cd SalesSavvyBackEnd
```

### 3. Configure MySQL Database

Update the **application.properties** file:

```
spring.datasource.url=jdbc:mysql://localhost:3306/salessavvyapp
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 4. Run the Application

Using Maven:

```
mvn spring-boot:run
```

Or run the main class:

```
SalesSavvyApplication.java
```

Application runs on:

```
http://localhost:8080
```

---

## 🔗 Example API Endpoints

| Method | Endpoint | Description |
|------|------|------|
| GET | /products | Get all products |
| POST | /products | Add new product |
| GET | /cart | Get cart items |
| POST | /cart/add | Add product to cart |
| DELETE | /cart/remove | Remove product from cart |

---

## 🔗 Frontend Repository

Frontend built with React + Vite:

```
https://github.com/mallikarjunkamari/SalesSavvyFrontEnd
```

---

## 🎯 Future Improvements

- User authentication with JWT
- Order management system
- Payment gateway integration
- Product search and filtering
- Admin dashboard

---

## 👨‍💻 Author

Mallikarjun 

GitHub:
https://github.com/mallikarjunkamari/SalesSavvyBackEnd.git

---

## 📄 License

This project is created for **learning and portfolio purposes**.