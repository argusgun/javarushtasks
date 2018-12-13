package com.javarush.task.task24.task2413;

/*
Требования:
1. В классе Canvas должно быть создано приватное поле width типа int.
2. В классе Canvas должно быть создано приватное поле height типа int.
3. В классе Canvas должно быть создано приватное поле matrix типа char[][].
4. В классе Canvas должен быть создан публичный геттер для поля width.
5. В классе Canvas должен быть создан публичный геттер для поля height.
6. В классе Canvas должен быть создан публичный геттер для поля matrix.
7. В классе Canvas должен быть создан публичный сеттер для поля width.
8. В классе Canvas должен быть создан публичный сеттер для поля height.
9. В классе Canvas должен быть создан публичный сеттер для поля matrix.
10. В классе Canvas должен быть создан корректный публичный конструктор с двумя параметрами типа int (width и height).
 */

public class Canvas {

    private  int width, height;
    private  char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix=new char[height+2][width+2];
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

    public void clear()
    {
        this.matrix = new char[height + 2][width + 2];
    }

    public  void print()
    {
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    void setPoint(double x, double y, char c)
    {
        int intX = (int)Math.round(x);
        int intY = (int)Math.round(y);

/*        if ( (intX >= 0) && (intY >= 0) && (intX <= matrix[0].length) && (intY <= matrix.length) ) {
            matrix[intY][intX] = c;
        }*/

        if ( (intX < 0) || (intY < 0) || (intX > matrix[0].length) || (intY > matrix.length) ) {

        }
        else {
            matrix[intY][intX] = c;
        }
    }

    void drawMatrix(double x, double y, int[][] matrix, char c)
    {
        int height = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 1)
                    setPoint(x + j, y + i, c);
            }
        }
    }
}
