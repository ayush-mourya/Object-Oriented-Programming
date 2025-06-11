
# 📚 Library Management System

A simple Java-based **Library Management System** that supports **both CLI and GUI** interaction modes. Designed to handle essential library operations such as managing members, books, and borrow/return activities.

---

## 🧭 Overview

This project simulates the operations of a real-world library using core Java concepts such as:
- Object-Oriented Programming (OOP)
- Collections (like `HashMap`, `ArrayList`)
- Swing-based GUI (`LibraryGUIApp.java`)
- Command-Line Interface (`LibrarySystem.java`)

---

## 📁 Files

| File Name            | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `LibraryGUIApp.java` | Java Swing-based GUI Application providing interactive panels for users.   |
| `LibrarySystem.java` | Command-line interface version of the library system.                      |
| `Member.java`        | Represents a member, with personal info and a list of borrowed books. |
| `Book.java`          | Represents a book with ID, title, author, and available copies.            |
| `BorrowedBook.java`  | Tracks each borrowed book along with its due date and issue date.          |
| `User.java`          | Superclass containing shared attributes like `name` and `phone`.         |
| `Person.java`        | interface for common member and librarian person methods|
| `Librarian.java`     | Represents a librarian with privileges to manage books and members.        |


---

## 👤 Member Functionalities

A registered member can:

- 🔍 **View Available Books**  
  Lists all books with title, author, available copies, and ID.

- 📕 **List My Borrowed Books**  
  Shows books currently borrowed by the member.

- ➕ **Issue a Book**  
  Borrow a book by entering its ID, if available and no fine is due.

- ↩️ **Return a Book**  
  Return a book using its ID. If overdue, a fine is calculated.

- 💳 **Pay Fine**  
  Clear any pending dues before issuing a new book.

- ❌ **Logout**  
  Exit from member portal.

---

## 👨‍🏫 Librarian Functionalities

Librarians can:

- ➕ **Register Member**  
  Add a new member with name, age, and phone number.

- ➖ **Remove Member**  
  Remove a member using their phone number.

- ➕ **Add Book**  
  Enter book title, author, and number of copies to add a new book.

- ➖ **Remove Book**  
  Remove a book from the system using its ID.

- 👥 **View All Members**  
  Display member details including their borrowed books.

- 📚 **View All Books**  
  Lists all books in the library with their current availability.

- ⬅️ **Back to Home**  
  Return to the home page.

---

## 💡 Features

- Unique IDs for each book and member.
- Borrowing system with automatic fine calculation.
- Book availability tracking.
- Dual interfaces: GUI and CLI.
- Neat code structure for scalability.

---

## 🖼️ GUI

![Home Page](GUI_Panels\Home.png)  
![Librarian Dashboard](GUI_Panels\librarian.png)  
![Member Login](GUI_Panels\member_login.png)  
![Member Dashboard](GUI_Panels\member_dashboard.png)




---
---
---

# ZooBuddies Management System

A comprehensive Java application for managing zoo operations, visitors, and attractions with both admin and visitor interfaces.

## Features

### 🎪 Attraction Management
- Add/remove/modify attractions  
- Set ticket prices  
- Track visitor counts per attraction  
- View attraction statistics  

### 📅 Event Scheduling
- Schedule events for attractions  
- Set event timings and prices  
- Open/close events  
- Modify existing events  

### 🦓 Animal Management
- Add animals with types (Mammal/Amphibian/Reptile)  
- Set animal feeding sounds  
- Visitor interaction system  
- View/update animal details  

### 💰 Discounts & Deals
- Create category-based discounts (Minor/Senior)  
- Set up bulk ticket deals  
- Manage discount codes  
- View all active offers  

### 📊 Visitor System
- Registration & login  
- Membership purchase  
- Ticket buying with discounts  
- Animal interaction  
- Feedback submission  

### 📈 Analytics
- Visitor statistics  
- Revenue tracking  
- Top attractions ranking  
- Feedback review system  

## Class Structure

```
📦 ZooBuddies
├── 📄 AdminPanel.java       - All admin functionalities
├── 📄 VisitorPanel.java     - Visitor interface
├── 📄 Main.java             - Entry point
├── 📄 Attraction.java       - Attraction model
├── 📄 Event.java            - Event scheduling
├── 📄 Animal.java           - Base animal class
├── 📄 ZooAnimal.java        - Zoo animal implementation
├── 📄 Visitor.java          - Visitor model
├── 📄 Membership.java       - Membership types
├── 📄 Discount.java         - Discount system
├── 📄 Deal.java             - Bulk deals
└── 📄 Feedback.java         - Visitor feedback
```



## Interesting Features

### 🐾 Animal Interaction System:
- Visitors can "feed" animals to hear their sounds  
- Detailed animal information available  
- Categorized by type (Mammal/Amphibian/Reptile)  

### 🎟️ Smart Discount Application:
- Automatic minor/senior discounts  
- Bulk ticket deals  
- Stackable discount system  

### 📊 Real-time Analytics:
- Tracks top attractions  
- Calculates total revenue  
- Visitor statistics dashboard  

## Default Animals

The system comes pre-loaded with 6 animals:

- 🦁 Lion (Mammal) - "Roars!"  
- 🐘 Elephant (Mammal) - "Trumpets!"  
- 🐸 Frog (Amphibian) - "Croaks!"  
- Salamander (Amphibian) - "Hisses softly!"  
- 🐍 Snake (Reptile) - "Hisses!"  
- 🐊 Crocodile (Reptile) - "Growls!"  

## Future Enhancements

- 🖼️ Graphical user interface  
- 🗺️ Interactive zoo map  
- 🎪 Special event notifications  
- 📱 Mobile app integration  
- 🛒 Gift shop system  

## GUI Implementation

The GUI for ZooBuddies is implemented using Java Swing. Below are screenshots of the key interfaces:

### 🏠 HomeFrame
![HomeFrame](GUI_Panels\Main.png)

### 🛠️ AdminGUI
![AdminGUI](GUI_Panels\Admin.png)

### 👤 VisitorRegister/LoginGUI
![VisitorRegister/LoginGUI](GUI_Panels\Visitor_login_register.png)

### 🎟️ VisitorGUI
![VisitorGUI](GUI_Panels\Visitor.png)

