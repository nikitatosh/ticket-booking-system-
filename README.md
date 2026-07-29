# ticket-booking-system
A **CLI** based ticket booking system built in **Java** using **Gradle**. The application simulates a basic ticket reservation workflow, including user authentication, train search, ticket booking, ticket retrieval, and cancellation. Application stores data locally in **JSON files** using Jackson's `ObjectMapper`.

---

## ✨ Features

- 👤 User Registration
- 🔐 User Login with Password Hashing
- 🚆 Search Available Trains
- 🎫 Book Train Tickets
- 📄 Fetch/View Booked Tickets
- ❌ Cancel Booked Tickets
- 🚪 Logout

---

## 🛠️ Tech Stack

- Java
- Gradle
- Jackson ObjectMapper
- Local JSON Database

---

## 📂 Project Structure

```text
app/
└── src/
    ├── main/
    │   └── java/
    │       └── ticket/
    │           └── booking/
    │               ├── entities/
    │               ├── service/
    │               ├── menu/
    │               ├── utils/
    │               ├── localDb/
    │               └── App.java
    └── test/
```

---

## 📚 Concepts Learned

This project helped me understand and practice:

- Object-Oriented Programming (OOP)
- Java Collections (`List`, `Map`)
- Java Streams
- `Optional` for handling nullable values
- JSON Serialization & Deserialization using Jackson `ObjectMapper`
- Password Hashing (SHA-256)
- Exception Handling
- File Handling
- Gradle Project Structure


---

## 📸 Application Flow

```text
Register
    ↓
Login
    ↓
Search Trains
    ↓
Book Ticket
    ↓
View Tickets
    ↓
Cancel Ticket
    ↓
Logout
```

---

## ▶️ How to Run

### Clone the repository

```bash
git clone https://github.com/nikitatosh/ticket-booking-system-.git
```

### Navigate to the project

```bash
cd ticket-booking-system-
```

### Run the application

**Windows**

```bash
gradlew.bat run
```

**Linux / macOS**

```bash
./gradlew run
```
