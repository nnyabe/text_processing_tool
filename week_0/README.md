# Project Overview

This repository contains multiple Java-based projects that perform various operations related to matrix manipulations and statistics calculations. Each subdirectory corresponds to a different project, and the directory structure is as follows:


## Projects

### 1. [**Matrix Multiplication**](https://github.com/nnyabe/labs/tree/main/week_0/matrix_multiplication)
- **Directory**: `/matrix_multiplication`
- **Description**: This project implements matrix multiplication in Java. The main focus of this project is to efficiently multiply two matrices and return the resulting matrix. It handles edge cases like incompatible matrix sizes and prints the resulting matrix to the console.
- **Technologies Used**:
    - **Java**: The core programming language used for implementation.
- **Key Features**:
    - Matrix multiplication of any two matrices with compatible dimensions.
    - Validation of matrix sizes before performing multiplication.
    - Prints the resulting matrix in a readable format.

### 2. [**Peak Columns**](https://github.com/nnyabe/labs/tree/main/week_0/peak_columns)
- **Directory**: `/peak_columns`
- **Description**: This project identifies **peak-column elements** in a matrix. A **peak-column element** is an element that is both the **maximum in its row** and the **minimum in its column**. The program checks each row for the maximum value, then validates if that value is the minimum in its corresponding column.
- **Technologies Used**:
    - **Java**: The programming language used to solve the problem.
- **Key Features**:
    - Iterates over the matrix to find peak-column elements.
    - Checks if the maximum value in the row is the smallest value in its column.
    - Prints the peak-column elements with their 1-based index position.

### 3. [**Statistics of Grades**](https://github.com/nnyabe/labs/tree/main/week_0/statistics_of_grades)
- **Directory**: `/statistics_of_grades`
- **Description**: This project calculates and displays statistical information for a given array of student grades. It calculates the **maximum**, **minimum**, and **average** grades, and also provides a graphical representation of grade distribution across predefined ranges.
- **Technologies Used**:
    - **Java**: The core language used for implementing statistical calculations.
- **Key Features**:
    - Calculates maximum, minimum, and average grades from an array.
    - Displays a text-based graph showing the distribution of grades in various predefined ranges (`0-20`, `21-40`, `41-60`, `61-80`, `81-100`).
    - Handles invalid inputs and array boundaries with proper exceptions.

---
