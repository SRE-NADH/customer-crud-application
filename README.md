# Full Stack Customer CRUD Application
This is a full-stack customer CRUD (Create, Read, Update, Delete) application built with Spring Boot for the backend and React for the frontend. The application allows users to sign in, log in, add customer details, delete customers, and update customer information.

## Project Structure
The project is organized into two main folders: 

customer-crud-client: Contains the React frontend code.

customer-crud-server: Contains the Spring Boot backend code.

## Getting Started
### Prerequisites

[Node.js](https://nodejs.org/en) (v16 or higher)

[npm](https://www.npmjs.com/) (v6 or higher)

[Java Development Kit (JDK)]() (v17 or higher)

Spring Boot

### Setup
1. Clone the repository:

```
git clone https://github.com/SRE-NADH/customer-crud.git
cd customer-crud
```


2. Client (React Frontend):
```
cd customer-crud-client
npm install
```

3.Server (Spring Boot Backend):
```
cd customer-crud-server
./mvnw spring-boot:run
```


### Running the Application

1. Start the Spring Boot backend:

```
cd customer-crud-server
./mvnw spring-boot:run
```   

2. Start React Client
```
cd customer-crud-client
npm install
```

3. Open your browser and navigate to http://localhost:3000 to access the application.

## Features
* Sign In / Log In: Users can sign in or log in to access the customer management features.
* Customer Management:
  * Add Customer: Users can add new customer details.
  * Delete Customer: Users can delete existing customers.
  * Update Customer: Users can update customer information.
  * View Customer List: Users can view the list of all customers.
### JWT Authentication

JWT authentication is implemented for securing API calls. Each API endpoint requires a valid JWT token for authorization.

#### API Endpoints

- **Sign In**: `/user/register`
- **Log In**: `/user/login`
- **Add Customer**: `/customers/create`
- **Delete Customer**: `/customer/delete/{id}`
- **Update Customer**: `/customer/update/{id}`
- **View Customer List**: `/customer/get/customers`    
#### Example JWT Authorization Header

Include the JWT token in the Authorization header for each API call:

```http
GET /customer/get/customers 
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiIsIn....
```

## Technologies Used
* Backend:
  * Spring Boot
  * JWT
  * Spring Security

* Frontend
   * React
   * React Router    

