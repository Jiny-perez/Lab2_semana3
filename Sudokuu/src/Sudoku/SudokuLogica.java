package Sudoku;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author marye
 */
public class SudokuLogica extends LogicaAbstract {
    
    public int obtener(int row, int col) {
        return tablero[row][col];
    }

    public boolean isPredeterminado(int row, int col) {
        return predeterminado[row][col];
    }

    public boolean asignar(int row, int col, int val) {
        if (predeterminado[row][col]) {
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

    protected void llenarTableroCompleto() {
        tablero = new int[9][9];
        resolver(0, 0);
    }

    private boolean resolver(int row, int col) {
        if (row == 9) {
            return true;
        }
        int nf = (col == 8) ? row+ 1 : row;
        int nc = (col + 1) % 9;

        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        mezclar(numeros);

        for (int v : numeros) {
            if (esValido(row, col, v)) {
                tablero[row][col] = v;
                if (resolver(nf, nc)) {
                    return true;
                }
                tablero[row][col] = 0;
            }
        }
        return false;
    }

    private void quitarNumeros(int cantidad) {
        Random rand = new Random();
        int removidos = 0;

        while (removidos < cantidad) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (tablero[row][col] != 0) {
                tablero[row][col] = 0;
                removidos++;
            }
        }
    }

    private void mezclar(int[] numeros) {
        Random rand = new Random();
        for (int i = numeros.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temp;
        }
    }

    public boolean isComplete() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
