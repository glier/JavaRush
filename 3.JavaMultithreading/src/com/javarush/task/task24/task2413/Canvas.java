package com.javarush.task.task24.task2413;

/**
 * Created by Илья Борозденец on 28.06.2017.
 */
public class Canvas {
    private int width, height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height+2][width+2];
    }

    public void setPoint(double x, double y, char c) {
        int ix = (int) Math.round(x);
        int iy = (int) Math.round(y);

        if (ix < 0 || iy < 0 || ix > matrix[0].length || iy > matrix.length)
            return;
        else this.matrix[iy][ix] = c;
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        int height = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 1)
                    setPoint(x + j, y + i, c);
            }
        }
    }

    public void clear () {
        this.matrix = new char[this.height+2][width+2];
    }

    public void print() {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }
}
