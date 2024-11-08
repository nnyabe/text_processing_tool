package week_0.statistics_of_grades;
import week_0.statistics_of_grades.StatisticsOfGrades;

public class TestStatisticsOfGrades {
    public static void main(String[] args) {
        int []grades = {2, 20, 21, 22, 23, 30, 48, 49, 50, 55, 60, 65, 72, 63, 76, 80, 68, 90, 85, 98};
        StatisticsOfGrades statistics = new StatisticsOfGrades(grades);

        statistics.displayCalculations();
        System.out.println();
        statistics.calculateStats();
        statistics.displayGraph();
    }

}
