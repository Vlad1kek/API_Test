# API Test Project

This project is an API test automation suite built to validate the behavior and reliability of various API endpoints. The suite ensures that APIs respond correctly, meet functional requirements, and handle both valid and invalid requests as expected. Both automated and manual tests are included to cover all essential aspects of API testing.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Manual Tests via Postman](#manual-tests-via-postman)
- [Project Structure](#project-structure)
- [Test Cases](#test-cases)
- [Swagger API Documentation](#swagger-api-documentation)
- [Reporting](#reporting)
- [CI/CD Integration](#cicd-integration)
- [Setup and Installation](#setup-and-installation)
- [Disclaimer](#disclaimer)

---

## Features

* Automated testing of multiple API endpoints.
* Validates HTTP methods such as GET, POST, PUT, and DELETE.
* JSON response validation.
* Integration with Jenkins and Maven for continuous testing.
* Manual tests via Postman.

---

## Technologies Used
* **Java:** Programming language for the test framework.
* **TestNG:** Test framework used for organizing and running tests.
* **RestAssured:** Library for testing REST APIs.
* **Maven:** Build automation tool for managing dependencies and running the test suite.
* **Allure:** Reporting tool to generate detailed test execution reports.

---

## Manual Tests via Postman

In addition to automated tests, created by me manual tests for key API functionalities are provided via Postman collections. You can review and run these tests through the links below:

* [Accounts](https://documenter.getpostman.com/view/34688119/2sA3e498jZ)
* [Attachments](https://documenter.getpostman.com/view/34688119/2sA3e498jb)
* [Bills](https://documenter.getpostman.com/view/34688119/2sA3e498jc)
* [Budgets](https://documenter.getpostman.com/view/34688119/2sA3e498jd)

---

## Project Structure

````bash
API_Test/
│
├── src/
│   └── test/
│       └── java/
│           └── api/
│               ├── endpoints/    # Contains endpoint classes for different API sections.
│               │   ├── AccountEndPoints.java         # Manages endpoints related to account operations.
│               │   ├── AttachmentEndPoints.java      # Manages endpoints related to attachments.
│               │   ├── BaseEndPoints.java            # Base class for common API endpoint definitions.
│               │   ├── BillsEndPoints.java           # Handles bill-related API operations.
│               │   └── BudgetEndPoints.java          # Handles budget-related API operations.
│               ├── payload/      # Classes for data modeling (Payloads) for different API requests/responses.
│               │   ├── Account.java                  # Model for account-related data.
│               │   ├── Attachment.java               # Model for attachment-related data.
│               │   ├── Bill.java                     # Model for bill-related data.
│               │   ├── Budget.java                   # Model for budget-related data.
│               │   └── Limit.java                    # Model for budget limits.
│               ├── test/         # Contains test classes for each endpoint.
│               │   ├── AccountTest.java              # Tests related to account operations.
│               │   ├── AttachmentTest.java           # Tests related to attachments.
│               │   ├── BaseTest.java                 # Base test class with common setup methods.
│               │   ├── BillTest.java                 # Tests related to bills.
│               │   ├── BudgetTest.java               # Tests related to budget operations.
│               │   └── DDTest.java                   # Data-driven test for various API validations.
│               └── utilities/    # Utility classes for data providers, reusable methods, etc.
│                   ├── DataProviders.java            # Class to provide test data for data-driven tests.
│                   └── XLUtility.java                # Utility class for reading Excel files.
├── resources/
│   ├── AccountJsonSchema.json                         # JSON schema for account-related responses.
│   ├── AttachmentJsonSchema.json                      # JSON schema for attachment-related responses.
│   ├── BillsJsonSchema.json                           # JSON schema for bill-related responses.
│   ├── BudgetJsonSchema.json                          # JSON schema for budget-related responses.
│   ├── BudgetLimitJsonSchema.json                     # JSON schema for budget limit-related responses.
│   └── routes.properties                              # Contains endpoint routes configuration.
├── testData/                                          # Directory for test data (e.g., Excel files, CSVs).
├── .gitignore                                         # Git ignore file to exclude unnecessary files from version control.
├── pom.xml                                            # Maven build configuration file.
├── README.md                                          # Project documentation.
└── testng.xml                                         # TestNG configuration file for managing test execution.
````

---

## Test Cases

Detailed test cases covering different scenarios can be found in this [Google Sheets document](https://docs.google.com/spreadsheets/d/1FPqjIdiSyT2MoWFtD7_PdnCeNTEn-p-YEMzq5Ftaevg/edit?gid=0#gid=0). These cases outline the various inputs, expected outputs, and error conditions that the APIs must handle.

---

## Swagger API Documentation

For detailed documentation of the API endpoints, refer to the [Swagger API Documentation](https://api-docs.firefly-iii.org/#/), which outlines all the endpoints, request/response structures, and possible status codes.

---

## Reporting

All test results are logged using Allure, which provides visual reports for:

* Passed, Failed, and Skipped Tests.
* API request and response logs.
* Detailed error messages for failed tests.

To view Allure reports:

````bash
mvn allure:serve
````

---

## CI/CD Integration

Test automation is integrated with Jenkins and Maven for continuous testing. You can view videos demonstrating the test execution process:

* **Jenkins Test Execution:** [Watch the video](https://www.youtube.com/watch?v=mtiF7YeHNCs&ab_channel=%D0%92%D0%BB%D0%B0%D0%B4)
* **Maven Test Execution:** [Watch the video](https://www.youtube.com/watch?v=gJ8p0FPgyQc&ab_channel=%D0%92%D0%BB%D0%B0%D0%B4)

---

## Setup and Installation

### Prerequisites

* **Java 11** or higher installed.
* **Maven** installed.
* Basic understanding of API testing and HTTP methods.

### Setup and Installation

1. **Installation and configuration of the test application is described** [here](https://docs.firefly-iii.org/how-to/firefly-iii/installation/docker/)

2. **Clone the repository:**

````bash
git clone https://github.com/Vlad1kek/API_Test.git
````

3. **Navigate to the project directory:**

````bash
cd API_Test
````

4. **Install dependencies:**

````bash
mvn clean install
````

5. **Run the tests:** Execute the test suite using Maven:

````bash
mvn test
````

6. **Generate Allure Reports:** After running the tests, you can generate Allure reports:
````bash
mvn allure:serve
````

---

## Disclaimer

This project is for educational and testing purposes only. It is not affiliated with any official API services or platforms.
