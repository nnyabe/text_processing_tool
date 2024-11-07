package com.arrays;

public class PeakColumns {
    private int[][]matA = new int[][]{};

    /**
     * PeakColumns(Contructor)
     * @param arr
     */
    public PeakColumns(int[][]arr){
        if(arr == null || arr.length == 0)
            throw new IllegalArgumentException("Pass valid array.");
        matA = arr;
    }
    /**
     * findPeak - Finds the peak column values in a matrix.
     * A peak-column element is the maximum in its row and the minimum in its column.
     */
    public void findPeak() {
        // Variable to track the maximum value in the current row (initially set to the smallest possible integer)
        int max = Integer.MIN_VALUE;

        // Outer loop: Iterate over each row of the matrix
        for (int i = 0; i < matA.length; i++) {
            // Boolean flag to determine if a peak-column element is found in the current row
            boolean found = true;

            // First loop (inner): Iterate over each element in the current row to find the maximum value
            for (int j = 0; j < matA[i].length; j++) {
                // Update 'max' with the maximum value found so far in the row
                max = Math.max(max, matA[i][j]);
            }

            // Second loop (inner): Iterate again to check each element equal to the maximum value found in the row
            for (int j = 0; j < matA[i].length; j++) {
                // If the current element in the row is equal to the maximum value
                if (max == matA[i][j]) {
                    // Third loop: Check the current column to see if the current element is the minimum in the column
                    for (int k = 0; k < matA.length; k++) {
                        // If there is any element in the column that is smaller than the current element, it's not a peak-column
                        if (matA[k][j] < matA[i][j]) {
                            found = false;  // Set 'found' to false
                            break;  // Exit the loop early as it's confirmed not a peak-column
                        }
                    }

                    // If no smaller element was found in the column, it means the current element is a peak-column
                    if (found) {
                        // Print the 1-based position of the peak-column element along with its value
                        System.out.printf("(%d, %d) = %d%n", i + 1, j + 1, matA[i][j]);
                    }
                }
            }

            // Reset the 'max' variable for the next row
            max = Integer.MIN_VALUE;
        }
    }

}

