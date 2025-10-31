package Sudoku;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author marye
 */
public class SudokuLogica extends LogicaAbstract {

    public boolean asignar(int f, int c, int v) {
        if (fijos[f][c]) {
            errores++;
            JOptionPane.showMessageDialog(null, "No puedes cambiar una casilla fija.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (v != solucion[f][c]) {
            errores++;
            JOptionPane.showMessageDialog(null, "Número incorrecto. Intenta otro.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        tablero[f][c] = v;
        return true;
    }

    public void generar(int pistas) {
        resetErrores();
        llenarTableroCompleto();

        for (int i = 0; i < 9; i++) {
            System.arraycopy(tablero[i], 0, solucion[i], 0, 9);
        }

        quitarNumeros(81 - pistas);

        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                fijos[f][c] = tablero[f][c] != 0;
            }
        }
    }

    @Override
    public void limpiarEntradas() {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (!fijos[f][c]) {
                    tablero[f][c] = 0;
                }
            }
        }
    }

    public boolean esValido(int f, int c, int v) {
        for (int i = 0; i < 9; i++) {
            if (tablero[f][i] == v || tablero[i][c] == v) {
                return false;
            }
        }
        int sr = (f / 3) * 3, sc = (c / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (tablero[i][j] == v) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void llenarTableroCompleto() {
        tablero = new int[9][9];
        Random rand = new Random();
        int f = 0, c = 0;

        while (f < 9) {
            if (tablero[f][c] == 0) {
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
                    if (esValido(f, c, v)) {
                        tablero[f][c] = v;
                        colocado = true;
                        break;
                    }
                }

                if (!colocado) {
                    tablero[f][c] = 0;
                    if (c == 0) {
                        f--;
                        c = 8;
                    } else {
                        c--;
                    }
                    continue;
                }
            }

            if (c == 8) {
                f++;
                c = 0;
            } else {
                c++;
            }
        }
    }

    private void quitarNumeros(int cuenta) {
        Random rand = new Random();
        int total = 81;
        int[] indices = new int[total];
        for (int i = 0; i < total; i++) {
            indices[i] = i;
        }

        for (int i = 0; i < total; i++) {
            int j = rand.nextInt(total);
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        int removed = 0;
        for (int i = 0; i < total && removed < cuenta; i++) {
            int idx = indices[i];
            int r = idx / 9, c = idx % 9;
            int backup = tablero[r][c];
            if (backup == 0) {
                continue;
            }

            tablero[r][c] = 0;
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
