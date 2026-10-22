
# Library Management System

**Course:** COMP228 – Java Programming  
**Project:** Midterm  


## 1. Project Description

This project is a console-based Library Management System developed using Java Object-Oriented Programming concepts.

It manages library members, staff, book reservations, late fees, and employee compensation.

## 2. Classes and Interfaces

 **Class and Interface Description**

 **Person**:  Abstract parent class that stores basic personal information and tracks the total number of people.

 **Member**:  Manages member registration, membership details, and outstanding fees. 

 **LibraryStaff**:  Abstract class that stores staff information and defines monthly compensation calculations. 

 **Librarian**:  Calculates late fees, processes books, handles reservations, and generates reports. 

 **SeniorLibrarian**:  Extends Librarian and includes an additional seniority allowance. 

 **LibraryAssistant**:  Calculates employee compensation including shift allowance. 

 **Reportable**:  Interface for generating reports. 

 **Reservable**:  Interface for reserving books. 

 **Main**:  Creates objects and demonstrates the application's functionality. 

## 3. OOP Concepts Demonstrated

- **Inheritance:** Single, multilevel, and hierarchical inheritance.
- **Abstraction:** Abstract classes and methods.
- **Encapsulation:** Private fields and getter methods.
- **Polymorphism:** Parent class references with different child objects.
- **Method Overloading:** Same method name with different parameters.
- **Method Overriding:** Redefining inherited methods.
- **Interfaces:** Implementing Reportable and Reservable.
- **Static and Final:** Shared counters and fields that cannot be reassigned.
- **Super:** Calling parent constructors and methods.

## 4. How to Run

1. Open the project in Eclipse IDE.
2. Make sure Java 21 or higher is configured.
3. Open `Main.java` in the `library` package.
4. Right-click and select Run As -> Java Application.
5. View the results in the Console.

## 5. Testing

The project includes tests for:

- Member registration and validation
- Late fee calculations
- Employee compensation
- Book reservations
- Static counters
