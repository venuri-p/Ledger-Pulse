# Ledger Pulse

**Ledger Pulse** is a personal finance management application designed to help users track income and expenses, manage budgets, organize transactions, and gain useful insights into their financial habits.

The project is being developed as a **full-stack monorepo application** with a **Spring Boot backend**, **React frontend**, and **PostgreSQL database**.

> 🚧 **Project Status: Ongoing Development**

---

## 📌 Project Introduction

Managing personal finances can become difficult when transactions, budgets, and recurring expenses are tracked manually across different applications or documents.

Ledger Pulse aims to provide a centralized platform where users can:

* Record and manage income and expenses
* Organize transactions using categories
* Set and monitor budgets
* Track recurring transactions
* Search and filter transactions
* View financial information through a dashboard
* Receive rule-based financial insights

The project is being developed incrementally, starting with the backend architecture and database design before implementing the complete frontend and additional features.

---

## 🚧 Project Status

Ledger Pulse is currently under active development.

### Completed

* PostgreSQL database setup
* Database schema design
* Spring Boot project setup
* Backend application architecture
* Database connectivity
* Initial data models and relationships

### In Progress / Planned

* JWT-based authentication
* User management
* Transaction management
* Category management
* Budget management
* Recurring transactions
* Transaction search and filtering
* Financial dashboard
* Rule-based financial insights
* React frontend
* CI/CD pipeline
* Testing and deployment

> Features listed under "In Progress / Planned" may change as development continues.

---

## ✨ Features

### 🔐 Authentication & User Management

Planned authentication functionality includes:

* User registration
* User login
* JWT-based authentication
* Secure API access
* Protected endpoints

---

### 💰 Transaction Management

Users will be able to:

* Create transactions
* View transactions
* Update transactions
* Delete transactions
* Categorize transactions
* Track income and expenses

---

### 🏷️ Category Management

Users will be able to organize transactions using categories.

Planned functionality includes:

* Create categories
* Update categories
* Delete categories
* Assign categories to transactions

---

### 💳 Budget Management

Users will be able to create and monitor personal budgets.

Planned functionality includes:

* Create budgets
* Set spending limits
* Monitor budget usage
* Track spending against budgets

---

### 🔄 Recurring Transactions

Ledger Pulse will support recurring financial transactions such as:

* Monthly subscriptions
* Regular bills
* Recurring income
* Other scheduled expenses

---

### 🔎 Search & Filtering

Users will be able to quickly find transactions using:

* Keyword-based search
* Date-based filtering
* Transaction type filtering
* Category-based filtering

---

### 📊 Dashboard

A financial dashboard is planned to provide an overview of:

* Total income
* Total expenses
* Spending patterns
* Budget usage
* Financial statistics

---

### 💡 Financial Insights

Ledger Pulse will provide **rule-based financial insights** based on users' transaction and spending data.

Examples may include:

* Identifying unusually high spending
* Highlighting frequently used categories
* Detecting budget usage patterns
* Providing simple spending recommendations

---

## 👥 User Roles

Ledger Pulse is primarily designed for individual users managing their own finances.

| Role     | Responsibilities                                                                                       |
| -------- | ------------------------------------------------------------------------------------------------------ |
| **User** | Manage personal transactions, categories, budgets, recurring transactions, and view financial insights |

Additional roles may be introduced if required during development.

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* REST APIs
* JWT Authentication

### Frontend

* React.js
* TypeScript
* Tailwind CSS
* HTML
* CSS

### Database

* PostgreSQL

### Development & Tools

* Git
* GitHub
* GitHub Actions
* Neon PostgreSQL

---

## 🏗️ Architecture

Ledger Pulse follows a **monorepo architecture**, where the frontend and backend are maintained within a single Git repository.

```text
                         Ledger Pulse
                              │
                 ┌────────────┴────────────┐
                 │                         │
          React Frontend             Spring Boot Backend
                 │                         │
                 │                      REST APIs
                 │                         │
                 └────────────┬────────────┘
                              │
                         PostgreSQL
                           Database
```

The frontend and backend are logically separated within the same repository while being developed and version-controlled together.

---

## 📂 Project Structure

```text
ledger-pulse/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── ledgerpulse/
│   │   │   │           ├── controller/
│   │   │   │           ├── service/
│   │   │   │           ├── repository/
│   │   │   │           ├── entity/
│   │   │   │           ├── dto/
│   │   │   │           ├── exception/
│   │   │   │           └── config/
│   │   │   │
│   │   │   └── resources/
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── hooks/
│   │   └── ...
│   │
│   ├── package.json
│   └── ...
│
├── .github/
│   └── workflows/
│
├── .gitignore
└── README.md
```

> The exact directory structure may evolve as development continues.

---

## ⚙️ Backend Architecture

The Spring Boot backend follows a layered architecture:

```text
                    REST Request
                         │
                         ▼
                  ┌─────────────┐
                  │ Controller  │
                  └──────┬──────┘
                         │
                         ▼
                  ┌─────────────┐
                  │   Service   │
                  └──────┬──────┘
                         │
                         ▼
                  ┌─────────────┐
                  │ Repository  │
                  └──────┬──────┘
                         │
                         ▼
                  ┌─────────────┐
                  │ PostgreSQL  │
                  └─────────────┘


## 🗄️ Database

Ledger Pulse uses **PostgreSQL** as its primary relational database.

The database is designed around core financial entities and their relationships.

A simplified conceptual structure is:

```text
                         User
                          │
             ┌────────────┼────────────┐
             │            │            │
             ▼            ▼            ▼
       Transactions    Budgets    Recurring
             │                     Transactions
             ▼
         Category
```

The database schema will continue to evolve as additional application requirements are implemented.

---

## 🔐 Authentication

Ledger Pulse plans to use **JSON Web Tokens (JWT)** for authentication and authorization.

The planned authentication flow is:

```text
User
 │
 │ Login
 ▼
Spring Boot API
 │
 │ Validate Credentials
 ▼
JWT Token
 │
 │ Authenticated Requests
 ▼
Protected REST APIs
```

Protected endpoints will require a valid JWT token.

---

## 🔎 Search & Filtering

A keyword-based transaction search and filtering system is planned.

Users will be able to search and filter transactions based on criteria such as:

* Keywords
* Date range
* Transaction type
* Category

This will make it easier to locate specific transactions and analyze spending patterns.

---

## 🔄 Development Roadmap

### Phase 1 — Foundation

* [x] Initialize Spring Boot project
* [x] Design PostgreSQL database schema
* [x] Configure database connectivity
* [x] Establish backend architecture
* [x] Create initial data models and relationships

### Phase 2 — Backend Core

* [ ] Implement JWT authentication
* [ ] Implement user management
* [ ] Implement transaction management
* [ ] Implement category management
* [ ] Implement budget management
* [ ] Implement recurring transactions
* [ ] Implement transaction search and filtering

### Phase 3 — Frontend

* [ ] Initialize React application
* [ ] Implement authentication UI
* [ ] Implement transaction management UI
* [ ] Implement category management UI
* [ ] Implement budget management UI
* [ ] Implement dashboard
* [ ] Implement search and filtering

### Phase 4 — Insights & Improvements

* [ ] Implement rule-based financial insights
* [ ] Improve validation and error handling
* [ ] Add comprehensive testing
* [ ] Improve application security
* [ ] Improve overall user experience

### Phase 5 — DevOps & Deployment

* [ ] Configure CI/CD pipeline
* [ ] Automate backend build and testing
* [ ] Automate frontend build and testing
* [ ] Deploy backend
* [ ] Deploy frontend
* [ ] Configure production database

---

## 🧪 Testing

Testing will be introduced throughout the development process.

Planned testing includes:

* Unit testing
* Integration testing
* REST API testing
* Backend service testing
* Frontend testing

---

## 🚀 CI/CD

A CI/CD pipeline is planned using **GitHub Actions**.

Because Ledger Pulse follows a monorepo architecture, the pipeline will eventually handle both frontend and backend components.

```text
                 Git Push / Pull Request
                           │
                           ▼
                    GitHub Actions
                           │
              ┌────────────┴────────────┐
              │                         │
              ▼                         ▼
       Backend Build & Test     Frontend Build & Test
              │                         │
              └────────────┬────────────┘
                           │
                           ▼
                       Deployment
```

The planned pipeline will automate:

* Backend build
* Frontend build
* Code quality checks
* Deployment

---

## 📌 Current Focus

The current development focus is on establishing the **backend foundation**, including:

* PostgreSQL database design
* Spring Boot architecture
* Database connectivity
* Core data models
* Backend development

Future development will progressively introduce authentication, core financial functionality, the React frontend, financial insights, and deployment.

---

## 📄 License

This project is currently being developed as a personal software engineering project.
