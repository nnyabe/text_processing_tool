package week_0.matrix_multiplication;

public class TestMatrixMultiplication {

    public static void main(String[] args) {
        MatrixMultiplication mat = new MatrixMultiplication();
        int[][] matA = mat.create2DMatrix();
        int[][] matB = mat.create2DMatrix();
        mat.multiplyMatrix(matA, matB);
        mat.displayResult();
    }
}

