package com.arrays;

import java.awt.dnd.InvalidDnDOperationException;

public class StatisticsOfGrades {
    private final int[] grades;
    private final int []stats = new int[5];

    public StatisticsOfGrades(int [] grades){
        // Check if the grades array is null or empty
        if (grades == null || grades.length == 0)
            throw new IllegalArgumentException("Input a correct array.");
        this.grades = grades;
    }

    /**
     * displayCalculations - Displays calculations on the grades array (max, min, average).
     * This method calculates and prints the maximum, minimum, and average grade
     * in the grades array. It also handles potential errors, such as an invalid or empty array.
     */
    public void displayCalculations() throws InvalidDnDOperationException {
        // Check if the grades array is null or empty
        if (grades.length == 0)
            // If the array is invalid, throw an exception with a descriptive message
            throw new InvalidDnDOperationException("Cannot perform operation with invalid arrays");

        // Print the heading "Values:"
        System.out.println("Values:");

        // Print the maximum grade by calling the calculateMax() method
        // The %d format specifier prints an integer value
        System.out.printf("The maximum grade is %d%n", calculateMax());

        // Print the minimum grade by calling the calculateMin() method
        // The %d format specifier prints an integer value
        System.out.printf("The minimum grade is %d%n", calculateMin());

        // Print the average grade by calling the calculateAvg() method
        // The %.2f format specifier prints a floating-point number rounded to two decimal places
        System.out.printf("The average grade is %.2f%n", calculateAvg());
    }


    /**
     * displayGraph - Displays a text-based graphical representation of the stats array.
     * Each value is represented as a bar of hash marks ("#######") according to its magnitude.
     */
    public void displayGraph() throws InvalidDnDOperationException {
        // Check if 'stats' is null or empty
        if ( stats.length == 0)
            throw new InvalidDnDOperationException("No results found.");

        System.out.println("Graph: ");

        // Loop through the graph levels (from highest to lowest)
        for (int i = stats.length; i >= 0; i--) {
            // Print the current level of the graph
            System.out.printf("%4d >", i + 1);

            // Loop through the stats array and print hash marks or spaces
            for (int stat : stats) {
                System.out.printf("%10s", (stat > i) ? "#######" : "        ");
            }
            System.out.println(); // Move to the next level
        }

        // Print the horizontal axis
        System.out.println("     +-----------+---------+---------+---------+---------+");

        // Print the category labels for the horizontal axis
        System.out.printf("     I%7s    I%7s  I%7s  I%7s  I %7s I", "0-20", "21-40", "41-60", "61-80", "81-100");
    }


    /**
     * calculateStats - Makes a statistic out of the grades given.
     */
    public void calculateStats() throws  InvalidDnDOperationException{
        if( grades.length == 0)
            throw new InvalidDnDOperationException("No results found.");

        for(int grade : grades)//loops through the array.
            /* checks if the current grade is evenly divisible by 20.
            if it is, it divides by 21 and increment the index. If it's not,
            it divides by 20 and increment the index.
             */
            ++stats[((grade % 20) == 0) ? (grade/21) : grade/20];

    }

    /**
     * calculateMax - Checks for the maximum value in an array
     * @return the maximum value in the array of grades.
     */
    public int calculateMax() throws  IllegalArgumentException{
        /* Checks for a null or empty array */
        if( grades.length == 0)
            throw new IllegalArgumentException("Pass valid array.");//Throws an error.

        int maximum = Integer.MIN_VALUE;//pass the minimum int to the maximum.

        for(int mark : grades)//loops through all the grades.
            maximum = Math.max(mark, maximum);//compares each grade with the current max.
        return maximum;
    }

    /**
     * calculateMin - Checks for the minimum value in an array.
     * @return minimum value in the array of grades.
     */
    public int calculateMin() throws  IllegalArgumentException{
        /* Checks for a null or empty array */
        if( grades.length == 0)
            throw new IllegalArgumentException("Pass valid array.");//Throws an error.

        int minimum = Integer.MAX_VALUE;//passed the maximum value of int.

        /* loops through the grades and compares the current minimum to the
        grade and assigns the smallest to minimum
         */
        for(int mark : grades)
            minimum = Math.min(mark, minimum);

        return minimum;
    }

    /**
     * calculateAvg - Calculates the average grade of the students.
     * @return average score by students.
     */
    public  double calculateAvg() throws  IllegalArgumentException{
        /* Checks for a null or empty array */
        if( grades.length == 0)
            throw new IllegalArgumentException("Pass valid array.");//Throws an error.

        int total = 0;//holds the sum of the grades.

        for(int number : grades)//looping through the grades.
            total += number;//adds each grade up to the total grades.

        return (double)total / grades.length;//divide the total marks by the array's length and return.
    }
}
