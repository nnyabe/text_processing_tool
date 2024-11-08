package week_0.matrix_multiplication;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Scanner;

/* Creates 2 Matrics, Multiply and output the results. */
public class MatrixMultiplication {
    int [][] matC = new int[][]{};

    /**
     * displayResult - outputs the result to the user.
     * @throws InvalidDnDOperationException
     */
    public  void displayResult() throws InvalidDnDOperationException{
        if(matC == null || matC.length == 0)
            throw new InvalidDnDOperationException("No results found.");

        for( int[] rows : matC){
            System.out.print("| ");
            for ( int val : rows)
                System.out.printf("%d ", val);
            System.out.print(" |");
            System.out.println();
        }
    }


    /**
     * multiplyMatrix - Multiply matrices A and B.
     * @return :
     * @throws IllegalArgumentException
     */
    public void multiplyMatrix(int [][] matA, int[][] matB) throws IllegalArgumentException{
        if (matA.length == 0 || matB.length == 0)
            throw  new IllegalArgumentException("Pass a correct matrix");

        matC = new int[matA.length][matB[0].length];

        for (int x = 0; x < matA.length; x++){ //keeping track of matA's and matC's rows.
            for (int y = 0; y < matA.length; y++){ // Keeping track of matB and matC's columns.
                for(int z = 0; z < matA[0].length; z++){
                    matC[x][y] += matA[x][z] * matB[z][y];//Performs calculations and pass to matC.
                }
            }
        }
    }

    /**
     * create2DMatrix - Creates a 2D matrix using user inputs.
     * @return : A new 2D matrix.
     */
    public int[][] create2DMatrix(){
        Scanner input = new Scanner(System.in);

        int row, colum;
        System.out.println("\n*************Creating a new Array**************");
        System.out.print("Enter the number of rows of the Matrix: ");
        row = getInputSize();
        System.out.print("Enter the number of columns of the Matrix: ");
        colum = getInputSize();
        System.out.printf("Created a %d x %d Matrix... %n", row, colum);

        /* Creates the matrix with the user input sizes */
        int [][] matrix = new int [row][colum];
        for( int i = 0; i< matrix.length; i++){
            System.out.println("Enter the values in the row.");
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = input.hasNextInt() ? input.nextInt() : 0;
            }
        }
        return matrix;
    }

    /**
     * inputSize - Takes input to create the size of the array.
     * @return  : Returns the number input.
     */
    private int getInputSize(){
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        int num;
        do{
            num = input.hasNextInt() ? input.nextInt() : 0;
            if(num <= 0){
                System.out.println("Enter a number greater than 0 :");
                input.nextLine();
            }else {
                loop = false;
            }
        }while(loop);
        return num;
    }
}
