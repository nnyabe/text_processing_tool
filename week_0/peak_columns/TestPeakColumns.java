package week_0.peak_columns;

public class TestPeakColumns {
    public static void main(String[] args) {
        int [][] arr = {
                {3, 5, 6, 7, 7 },
                {4, 2, 2, 8, 9},
                {6, 3, 2, 9, 7 }
        };
       PeakColumns peak = new PeakColumns(arr);
       peak.findPeak();
    }
}
