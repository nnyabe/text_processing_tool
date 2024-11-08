package week_0.statistics_of_grades;
import java.awt.dnd.InvalidDnDOperationException;

public class StatisticsOfGrades {
    private final int[] grades;
    private final int []stats = new int[5];

    public StatisticsOfGrades(int [] grades){
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
        if (grades.length == 0)
            throw new InvalidDnDOperationException("Cannot perform operation with invalid arrays");

        System.out.println("Values:");

        System.out.printf("The maximum grade is %d%n", calculateMax());
        System.out.printf("The minimum grade is %d%n", calculateMin());
        System.out.printf("The average grade is %.2f%n", calculateAvg());
    }


    /**
     * displayGraph - Displays a text-based graphical representation of the stats array.
     * Each value is represented as a bar of hash marks ("#######") according to its magnitude.
     */
    public void displayGraph() throws InvalidDnDOperationException {
        if ( stats.length == 0)
            throw new InvalidDnDOperationException("No results found.");
        System.out.println("Graph: ");

        for (int i = stats.length; i >= 0; i--) {
            System.out.printf("%4d >", i + 1);
            for (int stat : stats) {
                System.out.printf("%10s", (stat > i) ? "#######" : "        ");
            }
            System.out.println();
        }

        System.out.println("     +-----------+---------+---------+---------+---------+");

        System.out.printf("     I%7s    I%7s  I%7s  I%7s  I %7s I", "0-20", "21-40", "41-60", "61-80", "81-100");
    }


    /**
     * calculateStats - Makes a statistic out of the grades given.
     */
    public void calculateStats() throws  InvalidDnDOperationException{
        if( grades.length == 0)
            throw new InvalidDnDOperationException("No results found.");

        for(int grade : grades) {
            int range =((grade % 20 == 0) ? grade / 21 : grade / 20 );
            ++stats[range];
        }

    }

    /**
     * calculateMax - Checks for the maximum value in an array
     * @return the maximum value in the array of grades.
     */
    public int calculateMax() throws  IllegalArgumentException{
        if( grades.length == 0)
            throw new IllegalArgumentException("Pass valid array.");

        int maximum = Integer.MIN_VALUE;

        for(int mark : grades)
            maximum = Math.max(mark, maximum);
        return maximum;
    }

    /**
     * calculateMin - Checks for the minimum value in an array.
     * @return minimum value in the array of grades.
     */
    public int calculateMin() throws  IllegalArgumentException{
        if( grades.length == 0)
            throw new IllegalArgumentException("Pass valid array.");

        int minimum = Integer.MAX_VALUE;

        for(int mark : grades)
            minimum = Math.min(mark, minimum);

        return minimum;
    }

    /**
     * calculateAvg - Calculates the average grade of the students.
     * @return average score by students.
     */
    public  double calculateAvg() throws  IllegalArgumentException{
        if( grades.length == 0)
            throw new IllegalArgumentException("Pass valid array.");//Throws an error.

        int total = 0;

        for(int number : grades)
            total += number;

        return (double)total / grades.length;
    }
}
