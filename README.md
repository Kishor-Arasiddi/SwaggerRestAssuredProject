# SwaggerRestAssuredProject — REST Assured API Framework (Java)

A structured REST API test automation framework using **REST Assured** and **Java**, built to test the [Swagger Petstore API](https://petstore.swagger.io/). The framework follows a clean layered architecture separating endpoint constants, request payload models, and test classes for **Pet**, **Store**, and **User** modules.

---

## Project Structure

```
SwaggerRestAssuredProject/
├── src/
│   └── test/
│       ├── java/
│       │   ├── api_endpoints/
│       │   │   ├── PetEndPoints.java        # REST Assured request methods for Pet API
│       │   │   ├── Routes.java              # Centralized API route/URL constants
│       │   │   ├── StoreEndPoints.java      # REST Assured request methods for Store API
│       │   │   ├── UserEndPoints.java       # REST Assured request methods for User API
│       │   │   └── UserEndPoints2.java      # Alternate User endpoint implementation
│       │   ├── api_payload/
│       │   │   ├── Pet.java                 # POJO model for Pet entity
│       │   │   ├── Store.java               # POJO model for Store/Order entity
│       │   │   └── User.java                # POJO model for User entity
│       │   └── api_test/
│       │       ├── PetTest.java             # Test cases for Pet API endpoints
│       │       ├── StoreTest.java           # Test cases for Store/Order API endpoints
│       │       ├── UserTest.java            # Test cases for User API endpoints
│       │       └── UserTest2.java           # Extended/alternate User API test cases
│       └── resources/
│           └── Routes.properties            # Externalized base URL and route properties
├── .gitignore
├── pom.xml                                  # Maven build and dependency configuration
└── testng.xml                               # TestNG suite configuration
```

---

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Maven
- IDE: IntelliJ IDEA or Eclipse
- Internet access (tests run against [https://petstore.swagger.io/v2](https://petstore.swagger.io/v2))

### Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd SwaggerRestAssuredProject
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run all tests**
   ```bash
   mvn test
   ```

4. **Run via TestNG suite**
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
   ```

---

### Key Dependencies (`pom.xml`)

```xml
<dependencies>
    <!-- REST Assured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.x.x</version>
        <scope>test</scope>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.x.x</version>
        <scope>test</scope>
    </dependency>

    <!-- Jackson (Serialization / Deserialization) -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.x.x</version>
    </dependency>
</dependencies>
```

---

## Framework Architecture

The project follows a **3-layer architecture**:

```
api_payload  (POJO Models)
     ↓
api_endpoints  (Reusable Request Methods)
     ↓
api_test  (Test Classes with Assertions)
```

### Layer 1 — `api_payload` (Data Models)
POJO classes representing the request/response body for each Petstore entity.

| Class | Entity |
|---|---|
| `Pet.java` | Pet — id, name, status, photoUrls, category, tags |
| `Store.java` | Order — id, petId, quantity, status, shipDate |
| `User.java` | User — id, username, firstName, lastName, email, password, phone |

### Layer 2 — `api_endpoints` (Request Methods)
Each class wraps REST Assured calls for a specific API module, keeping test classes clean.

```java
// Example from UserEndPoints.java
public static Response createUser(User payload) {
    return given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(payload)
        .when()
        .post(Routes.post_url);
}
```

| Class | Responsibility |
|---|---|
| `Routes.java` | Centralized URL/route constants loaded from `Routes.properties` |
| `PetEndPoints.java` | Create, update, find, delete Pet |
| `StoreEndPoints.java` | Place order, get inventory, delete order |
| `UserEndPoints.java` | Create, read, update, delete User |
| `UserEndPoints2.java` | Alternate User endpoint approach |

### Layer 3 — `api_test` (Test Classes)
TestNG test classes that use endpoint methods and assert responses.

| Test Class | API Module | Coverage |
|---|---|---|
| `PetTest.java` | Pet | Create, Read, Update, Delete pet |
| `StoreTest.java` | Store | Place order, Get inventory, Delete order |
| `UserTest.java` | User | Create, Login, Read, Update, Delete user |
| `UserTest2.java` | User | Extended user flow scenarios |

---

## Configuration

API routes and base URL are externalized in `resources/Routes.properties`:

```properties
base_url=https://petstore.swagger.io/v2
post_url=/user
get_url=/user/{username}
update_url=/user/{username}
delete_url=/user/{username}
```

This makes it easy to switch environments (dev, staging, prod) without touching the code.

---

## Technologies Used

| Tool | Purpose |
|---|---|
| REST Assured | API test automation |
| Java | Primary language |
| TestNG | Test execution & assertions |
| Jackson | JSON serialization / deserialization |
| Swagger Petstore API | Target application under test |
| Maven | Build and dependency management |

---

## Best Practices Followed

- **3-layer separation** — payload models, endpoint methods, and test logic are fully decoupled
- **POJO-based serialization** — type-safe request bodies and response deserialization
- **Centralized routes** — all URLs managed in `Routes.java` and `Routes.properties`
- **Reusable endpoint methods** — test classes stay concise and readable
- **TestNG suite** — `testng.xml` enables ordered and grouped test execution

---

## API Under Test

This framework tests the **Swagger Petstore API**:
- Base URL: `https://petstore.swagger.io/v2`
- Swagger UI: [https://petstore.swagger.io](https://petstore.swagger.io)
- Modules covered: **Pet**, **Store**, **User**
