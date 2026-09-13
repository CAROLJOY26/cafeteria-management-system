# 🍽️ Cafeteria Management System

A Java-based cafeteria management system designed to manage students, staff, menu items, orders, authentication, loyalty points, and reports through a user-friendly graphical interface.

---

## 📌 About the Project

The **Cafeteria Management System** is a desktop application developed using **Java Swing** and **MongoDB**.

The system provides separate functionality for students and staff, allowing students to register, log in, browse cafeteria items, place orders, and manage their loyalty points, while staff members can manage cafeteria operations.

The project demonstrates object-oriented programming, GUI development, database integration, authentication, and software project organization.

---

## ✨ Features

### 🎓 Student Features
- Student registration
- Student login
- Student authentication
- Browse cafeteria menu
- Place orders
- View orders
- Loyalty points management
- Student account management

### 👨‍💼 Staff Features
- Staff login
- Staff dashboard
- Manage students
- Manage menu items
- Manage orders
- View cafeteria information
- Generate reports

### 🔐 Authentication
- Student authentication
- Staff authentication
- Role-based access
- Separate student and staff dashboards

### 🗄️ Database Integration
- MongoDB database integration
- Student data persistence
- MongoDB Java Driver
- Local MongoDB database

---

## 🛠️ Technologies Used

- **Java 21**
- **Java Swing**
- **Maven**
- **MongoDB**
- **MongoDB Java Driver**
- **Git**
- **GitHub**
- **Object-Oriented Programming (OOP)**

---

## 📂 Project Structure

```text
cafeteria-management-system/
│
├── Authenticatable.java
├── AuthenticationService.java
├── CafeteriaApp.java
├── CafeteriaSystem.java
├── CafeteriaSystemGUI.java
├── LoginFrame.java
├── LoyaltyManager.java
├── MenuItem.java
├── MenuManager.java
├── MongoDBConnection.java
├── OrderItem.java
├── OrderManager.java
├── OrderService.java
├── ReportService.java
├── Staff.java
├── StaffDashboard.java
├── StaffLoginDialog.java
├── StaffManager.java
├── Student.java
├── StudentDashboard.java
├── StudentLoginDialog.java
├── StudentOrder.java
├── StudentManager.java
├── UITheme.java
│
├── screenshots/
│   ├── login.png
│   ├── student-dashboard.png
│   ├── cafeteria-menu.png
│   └── staff-dashboard.png
│
├── pom.xml
├── RUN_GUI.txt
└── README.md
