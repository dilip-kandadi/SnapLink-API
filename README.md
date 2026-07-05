# ⚡ SnapLink API

A high-performance, RESTful URL Shortener service engineered with **Java**, **Spring Boot**, and **MySQL**. SnapLink transforms long, unwieldy web addresses into compact, shareable links while tracking real-time click analytics and enforcing strict data persistence.

---

## 🚀 Engineering Highlights

* **Fast & Optimized Redirection:** Engineered lightweight endpoints to intercept short codes and perform low-latency HTTP redirects to original destination URLs.
* **Automated Click Analytics:** Implemented atomic click-counter tracking to monitor engagement and traffic for every shortened link.
* **Data Persistence & Integrity:** Built on **Spring Data JPA** and **Hibernate** with strict schema constraints (`nullable = false`) and auto-incremented primary key generation.
* **Clean API Architecture:** Follows a strict 3-Tier Layered Architecture (Controller, Service, Repository) utilizing robust DTO patterns for type-safe JSON serialization.

---

## 🛠️ Tech Stack

* **Language:** Java 25
* **Framework:** Spring Boot 4.0 (Spring Web, Spring Data JPA)
* **Database:** MySQL 8.0 / H2 Database
* **Connection Pooling:** HikariCP
* **Build Tool:** Maven
* **Boilerplate Reduction:** Lombok

---

## 🔌 API Documentation

### 1. Shorten a URL
Creates a new short link and initializes click tracking.

* **URL:** `/api/shorten`
* **Method:** `POST`
* **Headers:** `Content-Type: application/json`
* **Request Body:**
  ```json
  {
    "longUrl": "[https://www.google.com/search?q=spring+boot+best+practices](https://www.google.com/search?q=spring+boot+best+practices)"
  }
