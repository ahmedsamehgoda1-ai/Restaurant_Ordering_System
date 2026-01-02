# Restaurant Ordering System

A simple **Java console-based Restaurant Ordering System** using **JDBC** and **MySQL**.  
Allows users to view a menu, place orders, and save them into a MySQL database.
The Project structure is attached in the file named "Architecture.txt"

## Layers explanation
**Layer explanation:**

- `MAIN`: Entry point (console UI)
- `MODEL`: Data models (Menu, Order, OrderItem)
- `DAO`: Database access objects (CRUD operations)
- `SERVICES`: Business logic (coordinate DAO operations)
- `db`: Database connection management (JDBC)

---

## **Prerequisites**

1. **Java 8+** installed
2. **IntelliJ IDEA** (or any Java IDE)
3. **MySQL server** running (local or remote)
4. **MySQL Workbench** (optional, for database management)
5. **MySQL Connector/J** jar (or Maven dependency)

## Database Setup ##
1. Open MySQL Workbench and connect to your server.
2. Create the database:
```` mysql
CREATE DATABASE restaurantdb;
USE restaurantdb;
````
3. Create tables:
```` mysql
-- Menu items table
CREATE TABLE menu_items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  price DECIMAL(10,2) NOT NULL
);
-- Orders table
CREATE TABLE orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  totalprice DECIMAL(10,2) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Order items table
CREATE TABLE orderitems (
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  order_price DECIMAL(10,2) NOT NULL,
  order_quantity INT NOT NULL,
  FOREIGN KEY (order_id)
      REFERENCES orders(id)
      ON DELETE CASCADE
);


````
## Optional: Setup the menu ## 
The menu was automatically setup, but you can setup your own menu in the MenuDAO.java

## Running the project ##
1. Download and add MySQL Connector/J Jar to your Project libraries
2. Update your Application.properties with the database credentials
3. Run RestaurantApp.java from the MAIN package
