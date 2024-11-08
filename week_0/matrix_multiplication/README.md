# Matrix Multiplication Java Program
## Overview
This directory contains a Java program that demonstrates matrix multiplication. The program allows users to:

1. Create two matrices (MatA and MatB) through user input.
2. Multiply the two matrices.
3. Display the resulting matrix (MatC).
The core logic of the program validates user input, performs matrix multiplication, and outputs the result in a formatted way.

## Features
- **Matrix Creation**: The program prompts the user to input the dimensions (rows and columns) for two matrices and their values.
- **Matrix Multiplication**: The program multiplies two matrices, following the standard matrix multiplication rules.
- **Result Display**: The resulting matrix is displayed to the user in a formatted grid.

### Compile and Run the Program

Follow these steps to compile and run the program:

1. **Open a terminal or command prompt**.

2. **Navigate to the directory where the project is stored**:

   ```bash
   cd path/to/your/project

### Code Structure

#### MatrixMultiplication.java

This file contains the main logic of the program, with key methods for handling matrix multiplication and input/output.

#### Attributes:
- **`matA`**: A 2D array representing the first matrix.
- **`matB`**: A 2D array representing the second matrix.
- **`matC`**: A 2D array representing the resulting matrix after multiplication.

#### Methods:

- **`multiplyMatrix(int[][] matA, int[][] matB)`**: Performs matrix multiplication.  
  This method accepts two matrices (`matA` and `matB`), performs matrix multiplication, and stores the result in `matC`.

- **`create2DMatrix()`**: Prompts the user to create a 2D matrix.  
  This method asks the user for the number of rows and columns, then collects the matrix values for each row and column.

- **`displayResult()`**: Displays the resulting matrix to the user.  
  This method checks if `matC` is empty or null, throwing an exception if no result is found, and then prints the matrix in a formatted way.

- **`inputSize()`**: Helper method to handle user input for matrix dimensions.  
  This method ensures that the user enters a valid number greater than 0 for the matrix's rows or columns.

### Error Handling

The program includes error handling to ensure that invalid matrices (such as empty matrices) or non-integer inputs are handled gracefully.

- **Invalid Matrix**: Throws an `IllegalArgumentException` if the matrices are invalid (e.g., empty).
- **Empty Result**: Throws an `InvalidDnDOperationException` if the result matrix is empty.

## Sample Usage
When you run the program, it will prompt you to input the number of rows and columns for the matrices, followed by the individual elements of the matrices.
<pre>Enter the number of rows of the Matrix: 2
Enter the number of columns of the Matrix: 3
Created a 2 x 3 Matrix...

Enter the values in the row.
1 2 3
4 5 6

Enter the number of rows of the Matrix: 3
Enter the number of columns of the Matrix: 2
Created a 3 x 2 Matrix...

Enter the values in the row.
7 8
9 10
11 12

Multiplying the matrices...

Resulting Matrix (MatC):
| 58 64 |
| 139 154 |</pre>