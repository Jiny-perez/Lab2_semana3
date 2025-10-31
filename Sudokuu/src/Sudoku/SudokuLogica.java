package Sudoku;

import java.util.Random;

/**
 *
 * @author marye
 */
public class SudokuLogica extends LogicaAbstract {

    private int[][] tablero = new int[9][9];
    private int[][] Solucion = new int[9][9];
    private boolean[][] predeterminado = new boolean[9][9];
    private int errores;

    public int get(int row, int col) {
        return tablero[row][col];
    }

    public boolean isPredeterminado(int row, int col) {
        return predeterminado[row][col];
    }

    public int getErrores() {
        return errores;
    }

    public void resetErrores() {
        errores = 0;
    }

    public void set(int row, int col, int val) {
       
    }

    public void Clear() {

    }

    public void cargar(int[][] puzzle, int[][] sol) {

    }

    public boolean esValido(int row, int col, int val) {

    }

    public boolean resuelto() {

    }

    public void revelarSolucion() {

    }

    public boolean rellenar() {
        Random rand = new Random();
        int row = 0, col = 0;

        while (row < 9) {
            if (Solucion[row][col] == 0) {
                boolean colocado = false;
                int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};

                for (int i = 0; i < numeros.length; i++) {
                    int j = rand.nextInt(numeros.length);
                    int temp = numeros[i];
                    numeros[i] = numeros[j];
                    numeros[j] = temp;
                }

                for (int i = 0; i < numeros.length; i++) {
                    int v = numeros[i];
                    if (esValido(row, col, v)) {
                        Solucion[row][col] = v;
                        colocado = true;
                        break;
                    }
                }

                if (!colocado) {
                    Solucion[row][col] = 0;
                    if (col == 0) {
                        row--;
                        col = 8;
                    } else {
                        col--;
                    }
                    continue;
                }
            }

            if (col == 8) {
                row++;
                col = 0;
            } else {
                col++;
            }
        }
        return true;
    }

}
