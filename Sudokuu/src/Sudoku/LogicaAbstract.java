package Sudoku;

/**
 *
 * @author ljmc2
 */
public abstract class LogicaAbstract {
    public static final int SIZE = 9;

    public abstract void clear();
    public abstract void set(int row, int col, int val);
    public abstract int get(int row, int col);
    public abstract void cargar(int[][] puzzle, int[][] sol);
    public abstract boolean esValido(int row, int col, int val);
    public abstract boolean resuelto();
    public abstract void revelarSolucion();
}
