# Mini Restaurant Daily Menu and Order Tracking System Case
This is a full-stack restaurant application with a Java Spring Boot backend and a React-based frontend. It allows users to view menus for a specific day, create orders, update order status, and view order details. The application consists of the following components:

- **Backend**: Built with Java Spring Boot (RESTful API).
- **Frontend**: Built with React.js (SPA).
- **Testing**: Unit tests for backend and frontend functionalities.

---
### Backend (Java Spring Boot)

The backend is built with Java Spring Boot using the **RESTful API** architecture. The application follows the **Controller-Service-Repository** structure for organizing the code:

- **Controller**: The controller classes handle incoming HTTP requests, map them to service layer methods, and return the responses. The controllers are responsible for defining the API endpoints (such as `/menus`, `/orders`).
  
- **Service**: The service layer contains the business logic of the application. It acts as an intermediary between the controller and repository layers. The services process data, handle validation, and interact with repositories to fetch or modify the database.

- **Repository**: The repository classes interact with the database through Spring Data JPA. They define data access methods such as `findAll()`, `findById()`, `save()`, and others. The repositories abstract the database interactions and provide the necessary methods to persist and retrieve data.

This structure is designed to separate concerns and make the code more maintainable, testable, and scalable.



## Requirements of the Case 

The following features are required for the application:

1. **View Menus**: Users should be able to view menus for a specific day.
2. **Create Order**: Users should be able to create an order by selecting items from the menu.
3. **Update Order Status**: The restaurant should be able to update the order status (e.g., "In Progress", "Delivered").
4. **View Orders**: Both the user and restaurant should be able to view an order and its status.

## Running the Application

### Backend (Java Spring Boot)

1. **Prerequisites**:
   - Java 17 or higher
   - Maven

2. **Steps to run the backend**:
   - Navigate to the root of the backend project folder.
   - Open a terminal and run the following command to start the backend:
     ```bash
     mvn spring-boot:run
     ```
   - The backend will run on [http://localhost:8080](http://localhost:8080).

### Frontend (React.js)

1. **Prerequisites**:
   - Node.js and npm (Node Package Manager)

2. **Steps to run the frontend**:
   - Navigate to the `Restaurant-App-Frontend` folder.
   - Install the dependencies:
     ```bash
     npm install
     ```
   - Once the installation is complete, run the frontend app:
     ```bash
     npm start
     ```
   - The frontend will run on [http://localhost:3000](http://localhost:3000).
   ---

## Running the Tests

### Backend Tests

The backend tests are located in the `src/test` folder. To run the tests:

1. Navigate to the backend project folder.
2. Use Maven to run the tests:
   ```bash
   mvn test
