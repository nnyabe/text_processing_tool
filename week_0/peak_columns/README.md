# `PeakColumns` Class

The `PeakColumns` class is designed to find and print **peak-column elements** from a given 2D matrix. A peak-column element in a matrix is defined by two properties:

1. It is the **maximum** element in its **row**.
2. It is the **minimum** element in its **column**.

## Class Overview

### Constructor: `PeakColumns(int[][] arr)`

- **Parameters**:
    - `arr`: A 2D array of integers representing the matrix.

- **Description**:
    - Initializes the matrix `matA` with the given 2D array `arr`.
    - Throws an `IllegalArgumentException` if the array is `null` or empty.

### Method: `findPeak()`

- **Description**:
    - Finds and prints the **peak-column elements** in the matrix.
    - A peak-column element is both the **maximum** in its row and the **minimum** in its column.

- **Logic**:
    - The method iterates through each row to find the maximum element in the row.
    - Once the maximum element is found, it checks if it is the smallest element in the respective column.
    - If it is, the element is printed as a peak-column, along with its **1-based** position in the matrix.

## How the `findPeak()` Method Works

### Step-by-Step Breakdown

1. **Initialize `max`**:
    - A variable `max` is initialized to `Integer.MIN_VALUE` to track the maximum value in the current row.

2. **Iterate Through Rows**:
    - The outer loop iterates over each row (`i` represents the row index).

3. **Find Maximum in Row**:
    - The first inner loop iterates through the elements of the row and identifies the maximum element, updating the `max` value accordingly.

4. **Identify Potential Peak-Column Elements**:
    - The second inner loop checks each element in the row. If an element equals `max`, it is a candidate for being a peak-column element.

5. **Check Column for Minimum Value**:
    - A third loop checks the entire column of the candidate element to see if any element in the column is smaller. If no smaller element is found, the candidate is a peak-column element.

6. **Print Peak-Column Elements**:
    - If the element is confirmed to be the smallest in its column, it is printed along with its position in the matrix (1-based index).

7. **Reset `max`**:
    - After processing each row, the `max` variable is reset to `Integer.MIN_VALUE` to ensure it starts fresh for the next row.

## Example Walkthrough

Consider the following matrix:

```java
int[][] matrix = {
    {10, 20, 30},
    {50, 60, 40},
    {70, 80, 90}
};
```
## Example Walkthrough

### Steps:

#### **First Row**:
- **Maximum element** is `30`.
- Check if `30` is the smallest in the third column. No smaller element is found, so `(1, 3) = 30` is a peak-column element.

#### **Second Row**:
- **Maximum element** is `60`.
- Check if `60` is the smallest in the second column. A smaller element is found, so `(2, 2) = 60` is not a peak-column element.

#### **Third Row**:
- **Maximum element** is `90`.
- Check if `90` is the smallest in the third column. A smaller element is found, so `(3, 3) = 90` is not a peak-column element.

### Output:
```text
(1, 3) = 30
```
## Summary
The PeakColumns class is a solution to finding peak-column elements in a matrix, where an element must be both the maximum in its row and the minimum in its column. It efficiently checks each element in the matrix and prints the peak-column elements along with their 1-based indices.