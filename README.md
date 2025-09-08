# Swag Labs Automated Testing Project

## 📋 Overview
This project is an **automated testing framework** for the [Swag Labs](https://www.saucedemo.com/) web application. The purpose of this project is to validate core functionalities such as login, product selection, cart operations, checkout flow, and logout using **Selenium WebDriver** with **Java** and **TestNG**.

---

## 🚀 Features
✔ Automated tests for login, product addition, checkout, and logout  
✔ Data-driven testing using Excel files  
✔ Page Object Model (POM) design pattern for maintainable code  
✔ Integrated logging with **Log4j**  
✔ Reports generated with **Extent Reports**  
✔ Cross-browser support with **WebDriverManager**  
✔ CI/CD ready with **Jenkins** and **Maven**

---

## 📂 Project Structure
Swag-Labs-Project/
├── src/
│ ├── main/
│ │ └── java/com/swaglabs/
│ │ ├── base/ # Base classes and setup
│ │ ├── config/ # Configuration and property files
│ │ ├── pages/ # Page Object classes
│ │ ├── utils/ # Utility classes like Excel reader
│ ├── test/
│ └── java/com/swaglabs/
│ ├── tests/ # Test classes using TestNG
│ └── listeners/ # Test listeners for reports
├── testdata/ # Excel files for test data
├── reports/ # Test reports
├── .gitignore # Files to ignore
├── pom.xml # Maven dependencies
└── README.md # Project documentation

---

## 🛠 Tools & Technologies
✔ **Java 8 or higher**  
✔ **Selenium WebDriver**  
✔ **TestNG**  
✔ **Extent Reports**  
✔ **WebDriverManager**  
✔ **Apache POI** (for Excel handling)  
✔ **Log4j**  
✔ **Maven**  
✔ **Jenkins** (for CI/CD)  
✔ **GitHub** (for version control)

---

## ⚙️ Setup Instructions

### ✅ Prerequisites
- Install JDK 8 or above  
- Install Maven  
- Install Git  
- Set up Chrome browser (or other browsers as needed)

### ✅ Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Swag-Labs-Project.git
