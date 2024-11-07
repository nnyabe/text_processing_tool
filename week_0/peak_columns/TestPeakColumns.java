package com.arrays;

public class TestPeakColumns {
    public static void main(String[] args) {
        int [][] arr = {{12,  2,  4},
            {17, 10,  1},
            {92, 80, 79}};
        PeakColumns peak = new PeakColumns(arr);
        peak.findPeak();
    }
}
