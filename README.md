# Weekly Java Projects

This repository contains a collection of Java projects, each organized by week. Each week's project is stored in a separate subdirectory under the `weeks` folder.

Each project demonstrates different aspects of Java programming, from basic syntax to more advanced concepts like object-oriented programming (OOP), algorithms, data structures, and more. Below is an overview of the project structure and instructions on how to navigate and run each project.

---

## Table of Contents

1. [Repository Structure](#repository-structure)
2. [Projects Overview](#projects-overview)
3. [Setting Up and Running Projects](#setting-up-and-running-projects)
4. [Technologies Used](#technologies-used)
5. [Prerequisites](#prerequisites)


---

## Repository Structure

This repository follows the structure below:
```
    labs/week_#/project_name/
```


- **`/labs/`**: Contains individual subdirectories for each week's project. Each week is organized by the project or assignment you're working on.
    - Example: `week-1-fibonacci-calculator/`, `week-2-database-app/`, etc.
- **`README.md`**: This file, providing an overview of the repository.

---

## Projects Overview

Each week's project is self-contained in its own directory and follows a specific assignment or challenge.
The goal of these projects is to build proficiency in Java through consistent practice and project-based learning.

### Example Weekly Projects

- [**Week 0**: Core Java Fundamentals](https://github.com/nnyabe/labs/tree/main/week_0)
- [**Week 1**:OOP, Databases & DSA](https://github.com/nnyabe/labs/tree/main/week_1)

---

## Setting Up and Running Projects

Each individual project within the `weeks` directory contains its own setup and running instructions, but in general, to run any Java project, you'll need the following:
1. Clone the repository:
   ```bash
   git clone https://github.com/nnyabe/labs.git
   ```
2. Navigate to the specific week's directory:
    ```bash
      cd labs/week_0/
   ```
   
### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or later is recommended.
- **IDE (Optional but recommended)**: Any Java IDE such as IntelliJ IDEA, Eclipse, or NetBeans.
- **Build Tools (if applicable)**: For projects that use Maven or Gradle, ensure you have those tools installed.

## Technologies Used

This repository uses a variety of technologies, tools, and libraries to build and run Java projects. Below is an overview of the main technologies used across the different weekly projects.

### **Java Development Kit (JDK)**

- **Version**: Java 11 or later
- The core language for all projects in this repository. Java is an object-oriented, class-based programming language used for building a wide range of applications, from desktop to web and mobile.
- You can download the JDK from [AdoptOpenJDK](https://adoptopenjdk.net/) or install it via package managers.

### **Maven**

- **Version**: 3.x (or later)
- Maven is a popular build automation tool for Java projects. It simplifies project builds, dependency management, and more.
- It is commonly used to manage dependencies (external libraries), automate the build lifecycle (compile, test, package), and deploy artifacts to repositories.
- [Maven Official Site](https://maven.apache.org/)

#### Key Features:
- Dependency management for Java libraries.
- Build lifecycle management (compile, test, package, etc.).
- Plugin ecosystem for additional functionality (e.g., running tests, creating JAR files).

### **Gradle**

- **Version**: 6.x (or later)
- Gradle is another build automation tool that is widely used for Java projects. It's flexible and can be configured using both Groovy and Kotlin DSL (Domain Specific Language).
- Gradle is used for dependency management, building applications, and testing. It offers a faster build process compared to Maven due to its incremental build feature.
- [Gradle Official Site](https://gradle.org/)

#### Key Features:
- Incremental builds (faster builds).
- Extensible build scripts using Groovy or Kotlin DSL.
- Excellent support for multi-project builds.

### **JUnit**

- **Version**: JUnit 5 (or later)
- JUnit is a widely used testing framework for Java. It helps in writing and running repeatable tests to ensure code quality and correctness.
- In this repository, JUnit is used to implement unit tests, integration tests, and functional tests for Java projects.
- [JUnit Official Site](https://junit.org/)

#### Key Features:
- Annotations for test methods (`@Test`, `@BeforeEach`, `@AfterEach`, etc.).
- Support for parameterized tests, assertions, and test lifecycle management.
- Integration with build tools like Maven and Gradle for automated testing.

### **Spring Framework**

- **Version**: Spring Boot 2.x (or later)
- Spring is a comprehensive framework used to build Java applications. It provides features like dependency injection, transaction management, and more.
- Spring Boot simplifies the development of stand-alone, production-grade Spring-based applications with minimal configuration.
- [Spring Framework Official Site](https://spring.io/)

#### Key Features:
- Dependency injection and inversion of control.
- Web development with Spring MVC and REST APIs.
- Spring Boot for easier application setup and deployment.

### **Apache Commons Libraries**

- **Version**: 3.x (or later)
- Apache Commons is a collection of reusable, open-source Java libraries that provide utility classes for tasks like file I/O, string manipulation, and collection handling.
- Libraries like `commons-lang`, `commons-io`, and `commons-collections` are used to simplify common tasks.
- [Apache Commons Official Site](https://commons.apache.org/)

#### Key Libraries:
- **Commons Lang**: Provides helper utilities for string manipulation, numbers, arrays, etc.
- **Commons IO**: Utility classes for file and stream I/O operations.
- **Commons Collections**: Provides additional collection classes beyond the Java Collections Framework.

### **JDBC (Java Database Connectivity)**

- **Version**: JDBC 4.0 (or later)
- JDBC is the API used to connect and execute queries against databases in Java. It enables interaction with databases such as MySQL, PostgresSQL, SQLite, etc.
- JDBC allows Java applications to send SQL queries, retrieve results, and update records in relational databases.
- [JDBC Official Documentation](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)

#### Key Features:
- Establishing database connections.
- Executing SQL queries (SELECT, INSERT, UPDATE, DELETE).
- Handling transactions and result sets.

### **Git**

- **Version**: Latest stable version
- Git is a distributed version control system that tracks changes in code. It is used for collaboration, managing source code, and maintaining version histories.
- This repository uses Git for source code version control, and GitHub is used for hosting the repository.
- [Git Official Site](https://git-scm.com/)

#### Key Features:
- Branching and merging for team collaboration.
- Tracking and reverting changes in code history.
- Collaboration through pull requests and issues.

### **Lombok**

- **Version**: 1.x (or later)
- Lombok is a Java library that helps to reduce boilerplate code (e.g., getters, setters, constructors). It uses annotations to automatically generate code at compile-time.
- Commonly used to simplify Java classes, especially in projects with data transfer objects (DTOs) or entity classes.
- [Lombok Official Site](https://projectlombok.org/)

#### Key Features:
- **@Getter** and **@Setter**: Automatically generates getters and setters for fields.
- **@Data**: Combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, and `@RequiredArgsConstructor` in one annotation.
- **@NoArgsConstructor**, **@AllArgsConstructor**: Automatically generates constructors.

### **Other Libraries (if applicable)**

- **Gson**: A library to convert Java objects to JSON and vice versa. It's commonly used in projects involving APIs or data serialization.  
  [Gson GitHub](https://github.com/google/gson)
- **Log4j / SLF4J**: Popular logging libraries for Java applications, helping to log events for debugging and monitoring purposes.  
  [Log4j Official Site](https://logging.apache.org/log4j/2.x/), [SLF4J Official Site](https://www.slf4j.org/)

### **IDE (Integrated Development Environment)**

- **IntelliJ IDEA**: A popular Java IDE for developing Java applications. It supports features like code completion, debugging, version control, and integration with build tools like Maven and Gradle.  
  [IntelliJ IDEA Official Site](https://www.jetbrains.com/idea/)

- **Eclipse**: Another widely used Java IDE, known for its extensibility and support for various programming languages and tools.  
  [Eclipse Official Site](https://www.eclipse.org/)

---

### How These Technologies Are Used in This Repository

Each weekly project might make use of different combinations of the technologies above. For example:

- Week 0-2 might use just **Java** and **JUnit** for basic algorithm implementation.
- Week 1 could introduce **JDBC** for database interaction.
- Later weeks may include **Spring Boot** for web development, **Apache Commons** for utility methods, and **JUnit** for testing.

Each individual project within the `weeks` directory will have a `README.md` file detailing the specific technologies used for that week's assignment.

---

### Additional Resources

- **Java Documentation**: [Oracle Java Documentation](https://docs.oracle.com/javase/)
- **Maven Documentation**: [Maven Docs](https://maven.apache.org/guides/)
- **JUnit Documentation**: [JUnit 5 Docs](https://junit.org/junit5/docs/current/user-guide/)
- **Spring Framework Documentation**: [Spring Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/)
