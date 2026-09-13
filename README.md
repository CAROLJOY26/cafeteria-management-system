# 🍽️ Cafeteria Management System

A Java-based desktop application designed to manage a university cafeteria environment through a user-friendly graphical interface. The system supports student and staff management, menu items, ordering, loyalty points, reports, and MongoDB database integration.

---

## 📌 About the Project

The **Cafeteria Management System** is a university software project developed to practice **Object-Oriented Programming, GUI development, database integration, authentication, and software organization**.

The application provides separate functionality for students and staff, allowing students to register, log in, browse the cafeteria menu, place orders, and manage their cafeteria activity, while staff members can manage cafeteria operations.

---

## ✨ Features

### 👨‍🎓 Student Features

* Student registration
* Student login
* Browse cafeteria menu
* Place food orders
* View order information
* Loyalty points management
* Student account management

### 👩‍💼 Staff Features

* Staff authentication
* Staff management
* Menu management
* Order management
* Cafeteria administration
* Report generation

### 🔐 Authentication

* Student authentication
* Staff authentication
* Separate student and staff interfaces
* Login validation

### 🗄️ Database Integration

* MongoDB database integration
* Student data persistence
* MongoDB Java Driver
* Local MongoDB connection

---

## 🛠️ Technologies Used

| Technology              | Purpose                             |
| ----------------------- | ----------------------------------- |
| **Java 21**             | Main programming language           |
| **Java Swing**          | Graphical User Interface            |
| **Maven**               | Dependency and project management   |
| **MongoDB**             | Database                            |
| **MongoDB Java Driver** | Java–MongoDB communication          |
| **Git & GitHub**        | Version control and project hosting |

---

## 🏗️ Project Structure

The project is organized into separate classes based on their responsibilities:

```text
Cafeteria Management System
│
├── Authentication
│   ├── Authenticatable.java
│   ├── AuthenticationService.java
│   └── LoginFrame.java
│
├── Students
│   ├── Student.java
│   ├── StudentManager.java
│   ├── StudentDashboard.java
│   └── StudentLoginDialog.java
│
├── Staff
│   ├── Staff.java
│   ├── StaffManager.java
│   ├── StaffDashboard.java
│   └── StaffLoginDialog.java
│
├── Menu
│   ├── MenuItem.java
│   └── MenuManager.java
│
├── Orders
│   ├── Orderable.java
│   ├── OrderItem.java
│   ├── StudentOrder.java
│   ├── OrderManager.java
│   └── OrderService.java
│
├── Services
│   ├── LoyaltyManager.java
│   └── ReportService.java
│
├── Database
│   └── MongoDBConnection.java
│
├── GUI
│   ├── CafeteriaSystemGUI.java
│   └── UITheme.java
│
├── CafeteriaApp.java
├── CafeteriaSystem.java
├── pom.xml
└── .gitignore
```

---

## 🗄️ MongoDB Database

The application uses MongoDB for persistent student data.

### Database

```text
cafeteria_db
```

### Collection

```text
students
```

### Local Connection

```text
mongodb://localhost:27017
```

The MongoDB connection is managed through:

```text
MongoDBConnection.java
```

---

## 🖥️ Graphical User Interface

The application uses **Java Swing** to provide a desktop graphical interface.

The main GUI entry point is:

```text
CafeteriaSystemGUI.java
```

The interface includes separate student and staff workflows and provides access to the main cafeteria management features.

---

## ⚙️ How to Run the Project

### 1. Requirements

Make sure you have:

* Java 21 or later
* Maven
* MongoDB
* Git

### 2. Clone the Repository

```bash
git clone https://github.com/CAROLJOY26/cafeteria-management-system.git
```

```bash
cd cafeteria-management-system
```

### 3. Make Sure MongoDB Is Running

The application expects MongoDB to be available at:

```text
mongodb://localhost:27017
```

### 4. Compile the Project

```bash
mvn clean compile
```

### 5. Run the GUI

The main GUI class is:

```text
CafeteriaSystemGUI
```

The application can then be launched using the compiled classes and Maven-managed dependencies.

---

## 🔄 Application Workflow

```text
Application Start
       ↓
Cafeteria System GUI
       ↓
   Login / Register
       ↓
 ┌───────────────┐
 │               │
Student        Staff
 │               │
 ↓               ↓
Menu          Management
 │               │
 ↓               ↓
Orders        Reports
 │
 ↓
Loyalty Points
 │
 ↓
MongoDB
```

---

## 🎯 Learning Objectives

This project helped practice:

* Object-Oriented Programming
* Java class design
* Interfaces and inheritance
* GUI development with Java Swing
* Authentication and user management
* Database integration
* MongoDB CRUD operations
* Maven dependency management
* Separation of responsibilities
* Git and GitHub version control

---

## 🚀 Future Improvements

Possible future improvements include:

* Password hashing and stronger authentication
* Improved database persistence for all system data
* Advanced order history
* More detailed reports and analytics
* Search and filtering for menu items
* Improved GUI navigation
* Deployment with a remote MongoDB database

---


## 📸 Application Screenshots

### 🔐 Login
![Login](screenshots/login.png)

### 🎓 Student Dashboard
![Student Dashboard](screenshots/student-dashboard.png)

### 🍔 Cafeteria Menu
![Cafeteria Menu](screenshots/cafeteria-menu.png)

### 👨‍💼 Staff Dashboard
![Staff Dashboard](screenshots/staff-dashboard.png)


## 👩‍💻 Author

**Carol Joseph Gorgi**

GitHub: [CAROLJOY26](https://github.com/CAROLJOY26)
