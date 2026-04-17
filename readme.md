# GreenVibe 🌿

Welcome to **GreenVibe**! A full-featured online store where plant enthusiasts can browse and purchase a wide variety of Plants, Seeds, and Planters. 

## 📖 Description
GreenVibe aims to bring nature closer to you. It provides a seamless e-commerce experience including a rich product catalog, a shopping cart, basic order management, and administrative features to manage inventory. 

## ✨ Features
* **User Management:** Basic registration and login for customers.
* **Product Catalog:** Browse products across various categories (Indoor Plants, Outdoor Plants, Seeds, Planters, Pots).
* **Shopping Cart:** Add, update, and remove items in the cart before checkout.
* **Order Management:** Place orders, manage cart conversion to orders, and track order details.
* **Admin Features:** Manage products, categories, stock levels, and view customer orders.

## 🏗️ Main Entities (Data Model)
* **Category:** Groupings for products (e.g., Indoor Plants, Outdoor Plants, Seeds, Planters).
* **Product:** The items for sale (includes `name`, `description`, `price`, `stock`, `imageUrl`, and associated `Category`).
* **User / Customer:** Stores customer details for registration, login, and order history.
* **Cart:** Represents a user's active shopping session.
* **CartItem:** Individual products and quantities within a Cart.
* **Order:** A finalized purchase linked to a User.
* **OrderItem:** Historic record of products and prices at the time of order placement.

## 💻 Tech Stack
* **Backend:** Java 17, Spring Boot (WebMVC, Data JPA, Validation)
* **Database:** MySQL
* **Utilities:** Lombok, Maven Wrapper

## 🚀 Getting Started

### Prerequisites
* **Java 17** or higher
* **MySQL Server** (running locally or remotely)

### Installation & Setup
1. **Clone the repository:**
   ```bash
   git clone https://github.com/navam142/GreenVibe.git
   cd GreenVibe
   ```

2. **Configure Database:**
   Update the `src/main/resources/application.properties` file with your MySQL database credentials. You can add the following basic setup:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/greenvibe_db?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=your_mysql_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and Run:**
   You can run the application using the included Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the Application:**
   The server will start by default on `http://localhost:8080`.