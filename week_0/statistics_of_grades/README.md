# `StatisticsOfGrades` Class

The `StatisticsOfGrades` class is designed to perform statistical operations on an array of student grades. It can calculate and display the following statistics:
- Maximum grade
- Minimum grade
- Average grade

Additionally, the class can generate a text-based graphical representation of the grade distribution across predefined ranges.

## Class Overview

### Constructor: `StatisticsOfGrades(int[] grades)`

- **Parameters**:
    - `grades`: A 1D array of integers representing the student grades.

- **Description**:
    - Initializes the class with the provided grades array.
    - Throws an `IllegalArgumentException` if the grades array is `null` or empty.

### Method: `displayCalculations()`

- **Description**:
    - Calculates and displays the **maximum**, **minimum**, and **average** grades.
    - If the grades array is invalid (empty), it throws an `InvalidDnDOperationException` with the message `"Cannot perform operation with invalid arrays"`.

- **Output**:
    - Prints the maximum grade, minimum grade, and average grade of the students in the array.

### Method: `displayGraph()`

- **Description**:
    - Displays a **text-based graphical representation** of the statistics (represented by bars of hash marks `#######`) in a simple graph.

- **Output**:
    - Prints a graphical representation of grade distribution across predefined ranges (`0-20`, `21-40`, `41-60`, `61-80`, `81-100`).
    - The height of each bar is proportional to the number of grades in each range.

- **Throws**:
    - Throws an `InvalidDnDOperationException` if the `stats` array is empty.

### Method: `calculateStats()`

- **Description**:
    - Calculates the grade distribution into the `stats` array, which has 5 buckets corresponding to the following grade ranges:
        - `0-20`
        - `21-40`
        - `41-60`
        - `61-80`
        - `81-100`

- **Throws**:
    - Throws an `InvalidDnDOperationException` if the grades array is empty.

### Method: `calculateMax()`

- **Description**:
    - Calculates and returns the **maximum** grade in the `grades` array.

- **Throws**:
    - Throws an `IllegalArgumentException` if the grades array is empty.

### Method: `calculateMin()`

- **Description**:
    - Calculates and returns the **minimum** grade in the `grades` array.

- **Throws**:
    - Throws an `IllegalArgumentException` if the grades array is empty.

### Method: `calculateAvg()`

- **Description**:
    - Calculates and returns the **average** grade in the `grades` array.

- **Throws**:
    - Throws an `IllegalArgumentException` if the grades array is empty.

## Example Usage

### Example 1: Calculating and Displaying Statistics

```java
int[] grades = {75, 82, 90, 60, 45, 70, 55, 89};

StatisticsOfGrades stats = new StatisticsOfGrades(grades);

// Display the calculations (max, min, average)
stats.displayCalculations();
```
*Output :*
```declarative
Values:
The maximum grade is 90
The minimum grade is 45
The average grade is 70.14
```
## Example Usage

### Example 2: Displaying the Graph

```
StatisticsOfGrades stats = new StatisticsOfGrades(grades);

// Calculate the stats (grade distribution across ranges)
stats.calculateStats();

// Display the graphical representation of the stats
stats.displayGraph();
```
*Output :*
```
Graph:
   5 >  #######        #######        #######        #######        #######
   4 >  #######        #######        #######        #######        #######
   3 >  #######        #######        #######        #######        #######
   2 >  #######        #######        #######        #######        #######
   1 >  #######        #######        #######        #######        #######
     +-----------+---------+---------+---------+---------+
     I   0-20   I   21-40  I   41-60  I   61-80  I   81-100 I

```
### Example 3: Handling Empty Array
```
int[] emptyGrades = {};

StatisticsOfGrades stats = new StatisticsOfGrades(emptyGrades);

// This will throw an IllegalArgumentException since the grades array is empty
stats.displayCalculations();
```
*Output :*
```
IllegalArgumentException: Pass valid array.
```

## Time Complexity

- **`displayCalculations()`**:
    - The time complexity is **O(n)**, where `n` is the number of grades, since it calculates the **maximum**, **minimum**, and **average** of the grades array by iterating over all elements.

- **`displayGraph()`**:
    - The time complexity is **O(1)** for generating the graph since it iterates through the `stats` array (which always has 5 elements).

- **`calculateStats()`**:
    - The time complexity is **O(n)**, where `n` is the number of grades, as it iterates over all the grades to populate the `stats` array.

- **`calculateMax()`, `calculateMin()`, and `calculateAvg()`**:
    - Each method has a time complexity of **O(n)**, where `n` is the number of grades in the array, as they iterate over all elements in the grades array to compute the desired statistic.

## Error Handling

- If the `grades` array is `null` or empty, an `IllegalArgumentException` is thrown with the message: `"Pass valid array."`.
- If the `stats` array is empty when **`displayGraph()`** or **`calculateStats()`** is called, an `InvalidDnDOperationException` is thrown with the message: `"No results found."`.
