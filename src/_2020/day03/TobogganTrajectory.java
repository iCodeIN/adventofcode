package _2020.day03;

import _2020.FileReader;

import java.io.IOException;
import java.nio.file.Paths;

public class TobogganTrajectory {
    private static final String FILE_LOCATION = "src/_2020/day03/input.txt";
    private static char[][] matrix = new char[0][];

    public static void main(String[] args) throws IOException {
        matrix = FileReader.getFileLinesAsMartix((Paths.get(FILE_LOCATION)));

//      Part One
        long numOfTrees = getNumberOfTrees(matrix, 1, 3);
        System.out.println(numOfTrees);

//      Part Two
        numOfTrees *= getNumberOfTrees(matrix, 1, 1);
        numOfTrees *= getNumberOfTrees(matrix, 1, 5);
        numOfTrees *= getNumberOfTrees(matrix, 1, 7);
        numOfTrees *= getNumberOfTrees(matrix, 2, 1);

        System.out.println(numOfTrees);
    }

    private static int getNumberOfTrees(char[][] matrix, int initRow, int initCol) {
        int numberOfTrees = 0;
        int matrixRows = matrix.length;
        int matrixCols = matrix[0].length;
        int row = initRow;
        int col = initCol;

        while (row < matrixRows) {
            if (col >= matrixCols)
                col = col % matrixCols;

            if (matrix[row][col] == '#')
                numberOfTrees++;

            col += initCol;
            row += initRow;
        }

        return numberOfTrees;
    }
}
