# Ledger Pulse

**Ledger Pulse** is a personal finance management application designed to help users track income and expenses, manage budgets, organize transactions, and gain useful insights into their financial habits.

The project is being developed as a **full-stack monorepo application** with a **Spring Boot backend**, **React frontend**, and **PostgreSQL database**, with planned **AI-powered financial features**.

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
* Receive AI-powered financial insights

The project is being developed incrementally, starting with the backend architecture and database design before implementing the complete frontend and additional features.

---

## 🚧 Project Status

Ledger Pulse is currently under active development.

### Completed

* PostgreSQL database setup & connectivity
* Database schema design with Flyway migrations
* Spring Boot project setup & layered architecture
* JWT-based authentication & authorization (Spring Security)
* User profile management & password reset via email
* Account management (multi-account support, account types, balances)
* Category management (income and expense categories)
* Transaction management (full CRUD operations)
* Keyword search and multi-parameter transaction filtering (type, category, date range)
* OpenAPI / Swagger API documentation
* Automated CI pipeline using GitHub Actions (Backend test build & Frontend lint/build)
* React + Vite frontend project initialization

### In Progress

* AI Transaction Assistant (Google GenAI / Gemini integration)

### Planned

* Budget management
* Recurring transactions
* Financial dashboard & analytics
* Complete React UI components & views
* Comprehensive unit & integration testing
* Automated CD pipeline & deployment

> Features listed under "Planned" may evolve as development continues.

---

## ✨ Features

### 🔐 Authentication & User Management

* User registration and login
* JWT-based stateless authentication & role security
* Password reset via email with expiring security tokens
* User profile management (profile info, profile picture, password update)

---

### 💼 Account Management

Users can organize their finances across multiple accounts:

* Create, view, update, and delete accounts (e.g., Bank, Cash, Savings)
* Track opening balances and currencies (default LKR)
* Activate and deactivate accounts
* Associate transactions directly with specific accounts

---

### 💰 Transaction Management

Users can track and manage income and expenditures:

* Create, view, update, and delete transactions
* Classify transactions as Income or Expense
* Associate transactions with accounts and categories
* Track timestamps, notes, and transaction metadata

---

### 🏷️ Category Management

Users can organize transactions into structured categories:

* Create custom categories per user
* Categorize by Income or Expense
* Update and delete categories
* Assign categories to transactions for reporting and filtering

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

### 🤖 AI-Powered Financial Features

Ledger Pulse will incorporate focused AI features to enhance the financial management experience.

#### AI Financial Insights

Analyze spending patterns, budget usage, and month-to-month changes to generate personalized financial insights and recommendations.

#### AI Financial Assistant

Allow users to ask natural-language questions about their financial data and receive contextual answers.

#### AI Transaction Categorization

Automatically suggest appropriate categories for transactions based on their descriptions.

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

* Java 21
* Spring Boot 3
* Spring Data JPA
* Spring Security & JWT
* Flyway Database Migrations
* REST APIs
* SpringDoc OpenAPI (Swagger UI)

### Frontend

* React.js
* TypeScript
* Vite
* Tailwind CSS
* HTML / CSS

### Database

* PostgreSQL
* Neon PostgreSQL (Cloud)

### AI

* Google GenAI SDK (Gemini API)
* Spring AI (Planned)

### Development & Tools

* Git & GitHub
* GitHub Actions (CI)
* Maven Wrapper
* Postman

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

AI services will be integrated through the Spring Boot backend.

```text
                         Spring Boot
                              │
                    ┌─────────┴─────────┐
                    │                   │
              Application          Google GenAI
                 Logic             (Gemini API)
                    │                   │
                    └────────┬─────────┘
                              │
                         AI Features
```

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
```

## 🗄️ Database

Ledger Pulse uses **PostgreSQL** as its primary relational database.

The database is designed around core financial entities and their relationships.

A simplified conceptual structure is:

```text
                         User
                          │
         ┌────────────────┼────────────────┐
         │                │                │
         ▼                ▼                ▼
      Accounts        Categories        Budgets
         │                │
         └────────┬───────┘
                  │
                  ▼
             Transactions
```

The database schema will continue to evolve as additional application requirements are implemented.

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
* [x] Configure database connectivity & Flyway migrations
* [x] Establish backend architecture
* [x] Create initial data models and relationships
* [x] Configure CI pipeline (Backend & Frontend)

### Phase 2 — Backend Core

* [x] Implement JWT authentication & authorization
* [x] Implement user management & profile updates
* [x] Implement password reset via email
* [x] Implement account management
* [x] Implement category management
* [x] Implement transaction management
* [x] Implement keyword search and multi-parameter transaction filtering
* [ ] Implement budget management
* [ ] Implement recurring transactions
* [ ] Implement financial dashboard & analytics summary endpoints

### Phase 3 — Frontend

* [x] Initialize React + Vite application
* [ ] Implement authentication UI
* [ ] Implement account management UI
* [ ] Implement transaction management UI
* [ ] Implement category management UI
* [ ] Implement budget management UI
* [ ] Implement financial dashboard
* [ ] Implement transaction search and filtering UI

### Phase 4 — AI Features & Improvements

* [x] Integrate Google GenAI SDK / Gemini API configuration
* [ ] Implement AI Transaction Assistant
* [ ] Implement AI Financial Insights
* [ ] Implement AI Transaction Categorization
* [ ] Improve validation and error handling
* [ ] Add comprehensive testing
* [ ] Improve application security
* [ ] Improve overall user experience

### Phase 5 — DevOps & Deployment

* [x] Automate backend build and testing (CI)
* [x] Automate frontend lint and build (CI)
* [ ] Configure CD pipeline
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
* AI feature testing

---

## 🚀 CI/CD

A CI/CD pipeline is being developed using **GitHub Actions**.

The CI pipeline has been implemented to automatically validate backend changes.

The planned CD pipeline will extend the automation to application deployment.

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

### Current CI/CD Status

* [x] Backend CI pipeline
* [x] Frontend CI pipeline
* [ ] CD pipeline
* [ ] Automated deployment

---

## 🌱 Development Workflow

Development follows a feature-based Git workflow.

```text
Create Feature Branch
        │
        ▼
Develop Feature
        │
        ▼
Commit Changes
        │
        ▼
Push Branch
        │
        ▼
Create Pull Request
        │
        ▼
CI Checks
        │
        ▼
Review & Testing
        │
        ▼
Merge into Main
```

The workflow will continue to integrate with the CI/CD pipeline as the project develops.

---

## 🎯 Project Goals

The main goals of Ledger Pulse are to:

* Build a practical full-stack financial application
* Apply software engineering principles to a real-world project
* Gain hands-on experience with Spring Boot and React
* Develop and consume REST APIs
* Work with PostgreSQL and relational database design
* Implement secure JWT authentication
* Practice clean and maintainable backend architecture
* Integrate AI into a practical software application
* Gain hands-on experience with LLM-based application development
* Learn CI/CD and deployment practices
* Build a scalable application through incremental development

---

## 📌 Current Focus

The current development focus is on:

* Implementing the **AI Transaction Assistant** with Google GenAI / Gemini API
* Developing **Budget Management** and **Recurring Transactions**
* Designing and building the **React frontend** interface & connecting backend REST APIs

---

## 📄 License

This project is currently being developed as a personal software engineering project.
