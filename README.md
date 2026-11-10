[readme.md](https://github.com/user-attachments/files/34631788/readme.md)
# Barracks Pantry API (Rancho Estoque API)

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)

A secure RESTful API built with Spring Boot to manage the food inventory of a military barracks pantry (Rancho). 

The system controls the stock distributed across two distinct depots (QR and QS) and implements strict Role-Based Access Control (RBAC) using JWT (JSON Web Tokens). It ensures that only authorized personnel (Sergeants) can modify the inventory, while standard users (Soldiers) have read-only access.

##  Features

*   **User Authentication:** Secure login using JWT.
*   **Role-Based Access Control:**
    *   `ADMIN` (Sergeant): Full access to create, read, and delete inventory items.
    *   `USER` (Soldier): Read-only access to view available stock.
*   **Inventory Management:** Track food items, quantities, expiration dates, and specific storage locations (QR or QS).
*   **Encrypted Passwords:** User passwords are encrypted in the database using BCrypt.

##  Technologies Used

*   **Java 21**
*   **Spring Boot 3.3.4** (Web, Data JPA, Security, Validation)
*   **PostgreSQL** (Relational Database)
*   **Auth0 Java-JWT** (Token generation and validation)
*   **Hibernate / JPA** (ORM)
*   **Lombok** (Boilerplate reduction)
*   **Maven** (Dependency Management)

##  Prerequisites

To run this project locally, you will need:
*   [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or higher
*   [PostgreSQL](https://www.postgresql.org/download/) installed and running
*   Maven

##  Setup and Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/estoque-api.git
   cd estoque-api
   ```

2. **Configure the Database:**
   Open pgAdmin or your terminal and create a new PostgreSQL database:
   ```sql
   CREATE DATABASE rancho_db;
   ```

3. **Update Application Properties:**
   Navigate to `src/main/resources/application.properties` and update the database credentials with your local PostgreSQL password:
   ```properties
   spring.datasource.password=YOUR_LOCAL_PASSWORD
   api.security.token.secret=your_super_secret_key
   ```

4. **Build the project:**
   ```bash
   mvn clean package
   ```

5. **Run the application:**
   ```bash
   java -jar target/estoque-api-0.0.1-SNAPSHOT.jar
   ```
   The API will be available at `http://localhost:8080`.

## 🔌 API Endpoints

### Authentication
| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | Public |
| POST | `/auth/login` | Authenticate user and return JWT | Public |

### Inventory (Alimentos)
*Requires `Authorization: Bearer <token>` header.*

| Method | Endpoint | Description | Access |
|---|---|---|---|
| GET | `/api/alimentos` | List all food items | ADMIN, USER |
| GET | `/api/alimentos/deposito/{deposito}` | Filter items by depot (QR or QS) | ADMIN, USER |
| POST | `/api/alimentos` | Add a new food item | ADMIN |
| DELETE | `/api/alimentos/{id}` | Delete a food item by ID | ADMIN |

---
*Developed as a portfolio project for backend architecture and security.*
