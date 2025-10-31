package Sudoku;

import java.util.Random;
import javax.swing.JOptionPane;

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

    public boolean set(int row, int col, int val) {
        if (predeterminado[row][col]) {
            errores++;
            JOptionPane.showMessageDialog(null, "No puedes cambiar una casilla fija.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (val != Solucion[row][col]) {
            errores++;
            JOptionPane.showMessageDialog(null, "Número incorrecto. Intenta otro.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        tablero[row][col] = val;
        return true;
    }

    public void clear() {

    }

    public void cargar(int[][] puzzle, int[][] sol) {

    }

    public boolean esValido(int row, int col, int val) {

        for (int i = 0; i < 9; i++) {
            if (tablero[row][i] == val || tablero[i][col] == val) {
                return false;
            }
        }
        int sr = (row/ 3) * 3, sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (tablero[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
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
