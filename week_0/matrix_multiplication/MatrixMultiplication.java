package com.arrays;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Scanner;

/* Creates 2 Matrics, Multiply and output the results. */
public class MatrixMultiplication {
    int [][] matA = new int[][]{};
    int [][] matB = new int[][]{};
    int [][] matC = new int[][]{};
//    /**
//     * Constructor : Forces user to give Arrays.
//     * @param matA
//     * @param matB
//     */
//    public MatrixMultiplication(int[][] matA, int[][]matB){
//        /* Checks for a invalid matrix(e.g empty matrix) and throws exception */
//        if (matA.length == 0 || matB.length == 0)
//            throw  new IllegalArgumentException("Pass a correct matrix");
//
//        this.matA = matA;
//        this.matB = matB;
//    }

    /**
     * displayResult - outputs the result to the user.
     * @throws InvalidDnDOperationException
     */
    public  void displayResult() throws InvalidDnDOperationException{
        /* Checks for a invalid matrix(e.g empty matrix) and throws exception */
        if(matC == null || matC.length == 0)
            throw new InvalidDnDOperationException("No results found.");

        /* Outputs the results of the resulting matrix */
        for( int[] rows : matC){
            System.out.printf("| ");
            for ( int val : rows)
                System.out.printf("%d ", val);
            System.out.printf(" |");
            System.out.println();
        }
    }


    /**
     * multiplyMatrix - Multiply matrices A and B.
     * @return :
     * @throws IllegalArgumentException
     */
    public void multiplyMatrix(int [][] matA, int[][] matB) throws IllegalArgumentException{
        /* Checks for a invalid matrix(e.g empty matrix) and throws exception */
        if (matA.length == 0 || matB.length == 0)
            throw  new IllegalArgumentException("Pass a correct matrix");

        /* Declares the matrix to contain the result. */
        matC = new int[matA.length][matB[0].length];

        for (int x = 0; x < matA.length; x++){ //keeping track of matA's and matC's rows.
            for (int y = 0; y < matA.length; y++){ // Keeping track of matB and matC's columns.
                for(int z = 0; z < matA[0].length; z++){
                    /* This loop does the magic, by tracking the values that should be
                    multiplied in the MatA and MatB column and rows respectively.
                     */
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
        /* Prompt user for the number of rows and columns */
        System.out.print("Enter the number of rows of the Matrix: ");
        row = inputSize();
        System.out.print("Enter the number of columns of the Matrix: ");
        colum = inputSize();
        System.out.printf("Created a %d x %d Matrix... %n", row, colum);

        /* Creates the matrix with the user input sizes */
        int [][] matrix = new int [row][colum];
        for( int i = 0; i< matrix.length; i++){
            System.out.println("Enter the values in the row.");
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = input.hasNextInt() ? input.nextInt() : 0; // takes the input if it's a valid integer.
            }
        }
//        input.close();//close to free the Scanner
        return matrix;
    }

    /**
     * inputSize - Takes input to create the size of the array.
     * @return  : Returns the number input.
     */
    private int inputSize(){
        Scanner input = new Scanner(System.in);
        boolean loop = true;//variable to keep track of the loop.
        int num;
        /* Performs the validation to all the input integers */
        do{
            num = input.hasNextInt() ? input.nextInt() : 0;
            /* Checks to validate the input and prompt user for another valid input */
            if(num <= 0){
                System.out.println("Enter a number greater than 0 :");
                input.nextLine();//skips the input given.
            }else {
                loop = false;
            }
        }while(loop);

//        input.close();//close to free the Scanner
        return num;
    }
}
