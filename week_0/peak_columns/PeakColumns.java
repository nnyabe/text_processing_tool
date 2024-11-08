package week_0.peak_columns;

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
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < matA.length; i++) {
            boolean found = true;

            // First loop (inner): Iterate over each element in the current row to find the maximum value
            for (int j = 0; j < matA[i].length; j++) {
                max = Math.max(max, matA[i][j]);
            }

            // Second loop (inner): Iterate again to check each element equal to the maximum value found in the row
            for (int j = 0; j < matA[i].length; j++) {
                if (max == matA[i][j]) {
                    // Third loop: Check the current column to see if the current element is the minimum in the column
                    for (int k = 0; k < matA.length; k++) {
                        if (matA[k][j] < matA[i][j]) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        System.out.printf("(%d, %d) = %d%n", i + 1, j + 1, matA[i][j]);
                    }
                }
            }
            max = Integer.MIN_VALUE;
        }
    }

}

