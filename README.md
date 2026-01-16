# Secure-Citizen-Management-System-Java-Servlet-JSP-Hibernate-


A secure, enterprise-style Java web application built using Servlets, JSP, Hibernate, AJAX, and MySQL, focused on strong authentication, session management, and dynamic data handling.

This project demonstrates real-world backend concepts such as single active session enforcement, forced logout, Hibernate ORM mappings, AJAX-based dynamic UI, and secure page access using servlet filters.

🚀 Features
🔐 Authentication & Session Management

User registration and login

Session-based authentication

Protected routes using servlet filters

Automatic logout on session expiry

Users cannot access internal pages without logging in

🚫 Single Active Session per User

A user cannot log in from more than one browser at a time

If already logged in, the user is prompted to:

Logout from the previous session and continue

Previous session is invalidated automatically

Runtime + database-backed session validation

🌍 Location Management (Hibernate Mappings)

Country → Province → District hierarchy

Province automatically maps to its country

Districts are dynamically loaded based on selected province

⚡ AJAX-Based Dynamic UI

Load districts without page refresh

Search users by CNIC dynamically

Smooth and responsive user experience

👤 User Management

Add users with CNIC-based identification

Auto-mapped geographic data

Hibernate ORM used for database operations

🔍 Dynamic CNIC Search

Search user records using CNIC

Results fetched via AJAX in real time

🛠️ Technologies Used

Backend: Java (Servlets)

Frontend: JSP, HTML, CSS, JavaScript, AJAX

ORM: Hibernate

Database: MySQL

Server: Apache Tomcat

Architecture: MVC, DAO Pattern

Security: Servlet Filters, Session-based Authentication

🧠 Application Architecture

MVC architecture for clean separation of concerns

DAO pattern for database access

Hibernate entity relationships

Servlet filters for authentication & forced logout

Runtime session tracking for single-session enforcement

📌 How Authentication Works

User logs in with CNIC and password

System checks if the user is already logged in

If logged in:

User is prompted to force logout the previous session

New session is created and old session is invalidated

All protected pages are accessible only with a valid session

🕒 Session Handling

Session timeout configured in web.xml

Automatic logout after inactivity

Session invalidated on logout or force logout

Runtime session data cleared on server restart

📂 Project Highlights

No page refresh during dynamic data loading

Secure page access using filters

Prevention of duplicate logins

Clean separation of concerns

Enterprise-style session handling logic

🎯 Learning Outcomes

Advanced session management in Java web applications

Hibernate ORM and entity relationships

AJAX integration with Servlets

Secure authentication using filters

Handling concurrency and session conflicts

📈 Future Improvements

Role-based access control (Admin/User)

Redis-based distributed session management

Migration to Spring Boot

RESTful APIs

UI enhancements

👨‍💻 Author

Mohammad Tahir
Junior Java Developer
