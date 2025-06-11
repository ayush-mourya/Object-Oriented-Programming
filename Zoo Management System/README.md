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
![HomeFrame](lib\Main.png)

### 🛠️ AdminGUI
![AdminGUI](lib\Admin.png)

### 👤 VisitorRegister/LoginGUI
![VisitorRegister/LoginGUI](lib\Visitor_login_register.png)

### 🎟️ VisitorGUI
![VisitorGUI](lib\Visitor.png)

