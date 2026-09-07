# 👨‍💼 Employee Management System

A menu-driven **Employee Management System** built using **Java, JDBC, and MySQL**.

This project is designed to practice real-world database operations using JDBC, including **CRUD operations, PreparedStatement, ResultSet, SQL filtering, aggregate functions, subqueries, and menu-driven programming**.

---

## 📌 Project Overview

The **Employee Management System** is a Java CLI-based application that allows users to manage employee records stored in a MySQL database.

The application provides options to:

- Add Employee
- View All Employees
- Search Employee by ID
- Update Employee
- Delete Employee
- Search Employees by Department
- Find Highest Salary
- Calculate Average Salary
- Count Total Employees
- Exit the application

The project was developed as a practical exercise to strengthen **Java + JDBC + MySQL** skills.

---

## 🚀 Features

### 1. Add Employee
Allows the user to add a new employee to the database.

Employee details include:

- Employee ID
- Employee Name
- Age
- Department
- Salary
- City

The application uses `PreparedStatement` to insert employee data.

---

### 2. View All Employees
Displays all employees stored in the database in a formatted table.

Example:

```text
======================================================================
                        EMPLOYEE DETAILS
======================================================================
ID    Name                 Age   Department      Salary       City
----------------------------------------------------------------------
101   Rahul                24    IT              45000.00     Pune
102   Priya                25    HR              40000.00     Mumbai
======================================================================
````

---

### 3. Search Employee

Allows the user to search for an employee using the employee ID.

SQL query used:

```sql
SELECT * FROM employees WHERE id = ?;
```

If the employee exists, complete employee details are displayed.

If the employee does not exist:

```text
Employee Not Found!
```

---

### 4. Update Employee

Allows the user to update an existing employee's:

* Name
* Age
* Department
* Salary
* City

SQL query:

```sql
UPDATE employees
SET name = ?, age = ?, department = ?, salary = ?, city = ?
WHERE id = ?;
```

---

### 5. Delete Employee

Allows the user to delete an employee by ID.

SQL query:

```sql
DELETE FROM employees
WHERE id = ?;
```

If the employee does not exist, the application displays:

```text
Not Delete Something is wrong
```

---

### 6. Search By Department

Allows the user to search for all employees belonging to a specific department.

Example:

```text
Enter Department : IT
```

SQL query:

```sql
SELECT * FROM employees
WHERE department = ?;
```

The application supports displaying multiple employees from the selected department.

---

### 7. Find Highest Salary

Finds the employee or employees having the highest salary.

The project uses a subquery with `MAX()`:

```sql
SELECT * FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);
```

This can return multiple employees when multiple employees have the same highest salary.

---

### 8. Calculate Average Salary

Calculates the average salary of all employees.

SQL query:

```sql
SELECT AVG(salary)
FROM employees;
```

Example:

```text
Average Salary : 54250.50
```

---

### 9. Count Employees

Counts the total number of employees stored in the database.

SQL query:

```sql
SELECT COUNT(*)
FROM employees;
```

Example:

```text
Employee Count : 15
```

---

### 10. Exit

Closes the application by closing the database connection and `Scanner`.

---

# 🛠️ Technologies Used

* **Java**
* **JDBC (Java Database Connectivity)**
* **MySQL**
* **IntelliJ IDEA**

---

# 📚 Concepts Practiced

## Java Concepts

* Classes
* Methods
* `Scanner`
* Loops
* `do-while`
* `switch`
* Conditional statements
* Exception handling
* Menu-driven programming

## JDBC Concepts

* JDBC connection
* `DriverManager`
* `Connection`
* `PreparedStatement`
* `ResultSet`
* `executeQuery()`
* `executeUpdate()`
* CRUD operations
* Database connection reuse
* SQL exception handling

## SQL Concepts

* `INSERT`
* `SELECT`
* `UPDATE`
* `DELETE`
* `WHERE`
* `MAX()`
* `AVG()`
* `COUNT()`
* Subqueries
* Filtering
* Aggregate functions

---

# 🗄️ Database Setup

## Step 1: Create Database

```sql
CREATE DATABASE company1;
```

## Step 2: Select Database

```sql
USE company1;
```

## Step 3: Create Employees Table

```sql
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    department VARCHAR(50),
    salary DOUBLE,
    city VARCHAR(50)
);
```

---

# 🔌 JDBC Connection

The project connects Java with MySQL using JDBC.

Current connection configuration in the application:

```java
String url = "jdbc:mysql://localhost:3306/company1";
String username = "root";
String password = "root";

Connection con =
        DriverManager.getConnection(url, username, password);
```

> Update the username and password according to your local MySQL configuration.

---

# 🔄 JDBC Application Flow

The basic flow of the project is:

```text
Java Application
       |
       v
   JDBC API
       |
       v
 DriverManager
       |
       v
   Connection
       |
       v
PreparedStatement
       |
       v
     MySQL
       |
       v
   ResultSet
```

---

# 🔧 CRUD Operations

CRUD stands for:

| Operation | SQL Command | JDBC Method       |
| --------- | ----------- | ----------------- |
| Create    | `INSERT`    | `executeUpdate()` |
| Read      | `SELECT`    | `executeQuery()`  |
| Update    | `UPDATE`    | `executeUpdate()` |
| Delete    | `DELETE`    | `executeUpdate()` |

---

# 🔐 Why PreparedStatement?

This project uses `PreparedStatement` for SQL operations.

Example:

```java
String sql = "SELECT * FROM employees WHERE id = ?";

PreparedStatement ps =
        con.prepareStatement(sql);

ps.setInt(1, id);

ResultSet rs =
        ps.executeQuery();
```

### Advantages

* Supports parameterized queries
* Makes handling user input easier
* Helps protect against SQL Injection
* Improves code readability
* Useful for repeated SQL execution

---

# 📖 PreparedStatement Example

### INSERT

```java
String sql =
        "INSERT INTO employees(id,name,age,department,salary,city) " +
        "VALUES(?,?,?,?,?,?)";

PreparedStatement ps =
        con.prepareStatement(sql);

ps.setInt(1, id);
ps.setString(2, name);
ps.setInt(3, age);
ps.setString(4, department);
ps.setDouble(5, salary);
ps.setString(6, city);

int row = ps.executeUpdate();
```

---

# 🔎 ResultSet

`ResultSet` is used to read data returned by a `SELECT` query.

Example:

```java
ResultSet rs = ps.executeQuery();

while (rs.next()) {
    System.out.println(
        rs.getInt("id") + " " +
        rs.getString("name") + " " +
        rs.getInt("age")
    );
}
```

The project uses `ResultSet` for:

* Viewing employees
* Searching employees
* Department filtering
* Highest salary
* Average salary
* Employee count

---

# ⚡ executeQuery() vs executeUpdate()

## executeQuery()

Used mainly for queries that return data.

Example:

```java
SELECT * FROM employees;
```

Java:

```java
ResultSet rs = ps.executeQuery();
```

---

## executeUpdate()

Used mainly for:

```text
INSERT
UPDATE
DELETE
```

Example:

```java
int row = ps.executeUpdate();
```

It returns the number of rows affected.

---

# 📋 Application Menu

When the program starts, it displays:

```text
=========================================
        EMPLOYEE MANAGEMENT SYSTEM
=========================================

1. Add Employee
2. View All Employees
3. Search Employee
4. Update Employee
5. Delete Employee
6. Search By Department
7. Find Highest Salary
8. Calculate Average Salary
9. Count Employees
10. Exit

=========================================
Enter Your Choice :
```

The menu continues until the user selects option `10`.

---

# 📂 Project Structure

```text
EMPLOYEE_MANAGEMENT_SYSTEM/
│
├── .idea/
│
├── out/
│
├── src/
│   └── Main.java
│
├── .gitignore
│
└── EMPLOYEE_MANAGEMENT_SYSTEM.iml
```

---

# ▶️ How to Run the Project

## 1. Install Java

Install JDK on your system.

Check:

```bash
java -version
```

---

## 2. Install MySQL

Make sure MySQL Server is installed and running.

---

## 3. Create Database

Run:

```sql
CREATE DATABASE company1;
```

Then:

```sql
USE company1;
```

---

## 4. Create Table

Run:

```sql
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    department VARCHAR(50),
    salary DOUBLE,
    city VARCHAR(50)
);
```

---

## 5. Configure Database Connection

Open `Main.java` and update:

```java
String url = "jdbc:mysql://localhost:3306/company1";
String username = "root";
String password = "root";
```

Use your own MySQL username and password.

---

## 6. Add MySQL JDBC Driver

Make sure the MySQL Connector/J JDBC driver is available in the project classpath.

---

## 7. Run the Application

Run:

```text
Main.java
```

The application will start in the console.

---

# 🧪 Example Usage

## Add Employee

```text
Enter Your Choice : 1

Enter Employee ID:
101

Enter Employee Name:
Rahul

Enter Employee Age:
24

Enter Department:
IT

Enter Salary:
45000

Enter City:
Pune

Employee Added Successfully!
```

---

## View Employees

```text
Enter Your Choice : 2

======================================================================
                        EMPLOYEE DETAILS
======================================================================
ID    Name                 Age   Department      Salary       City
----------------------------------------------------------------------
101   Rahul                24    IT              45000.00     Pune
102   Priya                25    HR              40000.00     Mumbai
======================================================================
```

---

## Search Employee

```text
Enter Your Choice : 3

Enter Id For Search :
101
```

Output:

```text
======================================================================
ID    Name                 Age   Department      Salary       City
----------------------------------------------------------------------
101   Rahul                24    IT              45000.00     Pune
======================================================================
```

---

## Update Employee

```text
Enter Your Choice : 4

Enter Id :
101

Enter New Name:
Rahul Patil

Enter New Age:
25

Enter New Department:
Development

Enter New Salary:
55000

Enter New City:
Pune
```

Output:

```text
Employee Updated
```

---

## Delete Employee

```text
Enter Your Choice : 5

Enter Id :
101
```

Output:

```text
Employee Delete Successfully...
```

---

# 🧠 SQL Queries Used

## Insert Employee

```sql
INSERT INTO employees(id,name,age,department,salary,city)
VALUES(?,?,?,?,?,?);
```

## Select All Employees

```sql
SELECT * FROM employees;
```

## Search Employee

```sql
SELECT * FROM employees
WHERE id = ?;
```

## Update Employee

```sql
UPDATE employees
SET name = ?, age = ?, department = ?, salary = ?, city = ?
WHERE id = ?;
```

## Delete Employee

```sql
DELETE FROM employees
WHERE id = ?;
```

## Search By Department

```sql
SELECT * FROM employees
WHERE department = ?;
```

## Find Highest Salary

```sql
SELECT * FROM employees
WHERE salary = (
    SELECT MAX(salary)
    FROM employees
);
```

## Average Salary

```sql
SELECT AVG(salary)
FROM employees;
```

## Count Employees

```sql
SELECT COUNT(*)
FROM employees;
```

---

# 🎯 Learning Outcomes

By building this project, I practiced:

* Connecting Java applications to MySQL
* JDBC architecture and database connectivity
* Creating reusable database connections
* Performing CRUD operations
* Using `PreparedStatement`
* Using `ResultSet`
* Writing parameterized SQL queries
* Using SQL aggregate functions
* Using subqueries
* Filtering records
* Handling database exceptions
* Building menu-driven CLI applications
* Organizing logic into separate methods
* Reusing a single database connection throughout the application

---

# 💡 Project Highlights

Some important practical concepts implemented in this project:

### ✅ CRUD

```text
Create → INSERT
Read   → SELECT
Update → UPDATE
Delete → DELETE
```

### ✅ Parameterized Queries

User input is passed using:

```java
?
```

with:

```java
PreparedStatement
```

### ✅ Reusable Connection

The application creates a connection once:

```java
Connection con = getConnection();
```

and passes it to the required methods.

### ✅ SQL Aggregation

The project uses:

```sql
MAX()
AVG()
COUNT()
```

### ✅ SQL Subquery

Highest salary is calculated using:

```sql
SELECT MAX(salary)
```

inside another query.

---

# 🚧 Future Improvements

Possible future improvements for this project:

* Add stronger input validation
* Add duplicate employee ID validation before insert
* Add salary range search
* Add employee search by name
* Add employee search by city
* Add sorting options
* Add pagination
* Add transaction management
* Add `commit()` and `rollback()`
* Use try-with-resources for JDBC resources
* Move database credentials to configuration
* Convert the CLI application into a Spring Boot REST API
* Add JPA/Hibernate
* Add authentication and authorization
* Add a web frontend

---

# 📌 Project Status

**Status: Completed ✅**

The current version successfully implements the core employee management operations using **Java + JDBC + MySQL**.

---

# 👨‍💻 Author

## Omkar Bhandalkar

**Java Full Stack Developer | Backend Developer**

Focused on:

* Java
* SQL
* JDBC
* Servlets
* Spring Boot
* REST APIs
* MySQL

---

# ⭐ Acknowledgement

This project was built as a practical learning project to strengthen Java backend and JDBC development skills.

---

# 📬 Feedback

Suggestions and improvements are always welcome.

Thank you for checking out this project! 🚀

