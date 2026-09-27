# Apni Shop Backend

Backend API for **Apni Shop**, an e-commerce application built using **Spring Boot, Spring Data JPA, Hibernate and MySQL**.

This backend provides REST APIs for the React frontend and replaces the earlier `json-server` based backend.

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* REST API
* Node.js (for data migration)

## Features

* User registration and login
* Product management
* Category and sub-category management
* Brand management
* Shopping cart
* Wishlist
* Checkout and order management
* Product testimonials
* FAQ management
* Newsletter subscription
* Contact form
* Website settings
* Admin product CRUD operations
* MySQL database integration
* REST APIs for React frontend

## API Endpoints

The backend provides REST APIs for the following modules:

| Module        | Endpoint        |
| ------------- | --------------- |
| Main Category | `/maincategory` |
| Sub Category  | `/subcategory`  |
| Brand         | `/brand`        |
| Product       | `/product`      |
| Feature       | `/feature`      |
| FAQ           | `/faq`          |
| Setting       | `/setting`      |
| Contact Us    | `/contactus`    |
| Newsletter    | `/newsletter`   |
| User          | `/user`         |
| Cart          | `/cart`         |
| Wishlist      | `/wishlist`     |
| Checkout      | `/checkout`     |
| Testimonial   | `/testimonial`  |

Each module supports standard CRUD operations where required:

```text
GET     /product
GET     /product/{id}
POST    /product
PUT     /product/{id}
DELETE  /product/{id}
```

## Database

The project uses **MySQL** as the database.

Create the database:

```sql
CREATE DATABASE apnishop_db;
```

Then update the database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/apnishop_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8000
```

Hibernate automatically creates and updates the required tables when the application starts.

## Project Structure

```text
src/main/java/com/apnishop/backend/

├── BackendApplication.java
│
├── config/
│   └── CorsConfig.java
│
├── controller/
│   ├── ProductController.java
│   ├── UserController.java
│   ├── CartController.java
│   ├── CheckoutController.java
│   └── ...
│
├── entity/
│   ├── Product.java
│   ├── User.java
│   ├── Cart.java
│   ├── Checkout.java
│   └── ...
│
└── repository/
    ├── ProductRepository.java
    ├── UserRepository.java
    ├── CartRepository.java
    └── ...
```

The application follows a simple layered structure:

```text
React Frontend
      ↓
REST Controller
      ↓
JPA Repository
      ↓
Hibernate
      ↓
MySQL
```

## Running the Backend

Clone the repository and open the project in your IDE or terminal.

Run the application using Maven:

```bash
mvn spring-boot:run
```

Or run:

```text
BackendApplication.java
```

After starting successfully, the backend will run on:

```text
http://localhost:8000
```

## Connecting React Frontend

The React frontend uses the backend URL from the `.env` file:

```env
VITE_APP_BACKEND_SERVER=http://localhost:8000
```

Start the React application separately:

```bash
npm install
npm run dev
```

The frontend and backend will then communicate through REST APIs.

## CORS Configuration

The backend allows requests from the React Vite development server.

Example:

```text
http://localhost:5173
```

CORS configuration is available in:

```text
config/CorsConfig.java
```

## Data Migration

The project also contains a migration script for moving existing `json-server` data into MySQL.

The old data is stored in:

```text
migration/data.json
```

First start the Spring Boot backend.

Then open a terminal inside the migration folder:

```bash
cd migration
```

Run:

```bash
node migrate.js
```

The migration script sends the existing data to the corresponding Spring Boot REST APIs.

The migration covers modules such as:

* Main Category
* Sub Category
* Brand
* Product
* Feature
* FAQ
* Setting
* Contact Us
* Newsletter
* User
* Cart
* Wishlist
* Checkout
* Testimonial

The existing string IDs are preserved during migration so that product, user, cart and order references remain consistent.

## Frontend + Backend

The complete application is structured as:

```text
Apni Shop
│
├── React Frontend
│   ├── React
│   ├── Redux
│   ├── Redux Saga
│   └── Vite
│
└── Spring Boot Backend
    ├── REST API
    ├── Spring Data JPA
    ├── Hibernate
    └── MySQL
```

## Main Functional Flow

### User

```text
Register/Login
      ↓
Browse Products
      ↓
Product Details
      ↓
Add to Cart / Wishlist
      ↓
Checkout
      ↓
Place Order
      ↓
Order History
```

### Admin

```text
Admin Login
     ↓
Manage Categories
     ↓
Manage Brands
     ↓
Manage Products
     ↓
Manage Users
     ↓
Manage Orders
```

## Important Notes

* The backend runs on port `8000` to match the existing React frontend configuration.
* MySQL is used for persistent data storage.
* JPA and Hibernate are used for database operations.
* REST APIs are used for communication between React and Spring Boot.
* The migration script is included to move existing `json-server` data to MySQL.
* Image fields currently store image paths/names. Actual file upload can be added later using `multipart/form-data`.
* Password handling should be improved with BCrypt hashing before using the application in a production environment.

## Future Improvements

Some features that can be added in future:

* Spring Security
* JWT authentication
* BCrypt password encryption
* Image upload API
* Role-based authorization
* Payment gateway integration
* Product search and filtering
* Pagination
* API validation
* Global exception handling
* Deployment with cloud database

## Author

**Amit Shukla**

Java Full Stack Developer
Java | Spring Boot | REST API | MySQL | React
