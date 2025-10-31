package Sudoku;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author marye
 */
public class SudokuLogica extends LogicaAbstract {

    private int[][] tablero = new int[9][9];
    private int[][] solucion = new int[9][9];
    private boolean[][] predeterminado = new boolean[9][9];

    public int obtener(int row, int col) {
        return tablero[row][col];
    }

    public boolean isPredeterminado(int row, int col) {
        return predeterminado[row][col];
    }

    public boolean asignar(int row, int col, int val) {
        if (fijos[row][col]) {
            errores++;
            JOptionPane.showMessageDialog(null, "No puedes cambiar una casilla fija.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (val != solucion[row][col]) {
            errores++;
            JOptionPane.showMessageDialog(null, "Número incorrecto. Intenta otro.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        tablero[row][col] = val;
        return true;
    }

    public boolean remover(int row, int col) {
        if (predeterminado[row][col]) {
            JOptionPane.showMessageDialog(null, "No puedes borrar una casilla inicial.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        tablero[row][col] = 0;
        return true;
    }

    public void generar(int pistas) {
        llenarTableroCompleto();

        for (int i = 0; i < 9; i++) {
            System.arraycopy(tablero[i], 0, solucion[i], 0, 9);
        }

        quitarNumeros(81 - pistas);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                predeterminado[row][col] = tablero[row][col] != 0;
            }
        }
    }

    public void limpiarEntradas() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!predeterminado[row][col]) {
                    tablero[row][col] = 0;
                }
            }
        }
    }

    public boolean esValido(int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (tablero[row][i] == val || tablero[i][col] == val) {
                return false;
            }
        }
        int sr = (row / 3) * 3, sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (tablero[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void llenarTableroCompleto() {
        tablero = new int[9][9];
        resolver(0, 0);
    }

    private boolean resolver(int f, int c) {
        if (f == 9) {
            return true;
        }
        int nf = (c == 8) ? f + 1 : f;
        int nc = (c + 1) % 9;

        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        mezclar(numeros);

        for (int v : numeros) {
            if (esValido(f, c, v)) {
                tablero[f][c] = v;
                if (resolver(nf, nc)) {
                    return true;
                }
                tablero[f][c] = 0;
            }
        }
        return false;
    }

    private void quitarNumeros(int cantidad) {
        Random rand = new Random();
        int removidos = 0;

        while (removidos < cantidad) {
            int f = rand.nextInt(9);
            int c = rand.nextInt(9);
            if (tablero[f][c] != 0) {
                tablero[f][c] = 0;
                removidos++;
            }
        }
    }

    private void mezclar(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    @Override
    public boolean isComplete() {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (tablero[f][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
