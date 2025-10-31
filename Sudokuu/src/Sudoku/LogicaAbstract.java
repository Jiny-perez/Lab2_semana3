package Sudoku;

/**
 *
 * @author ljmc2
 */
public abstract class LogicaAbstract {
    protected int[][] tablero = new int[9][9];
    protected int[][] solucion = new int[9][9];
    protected boolean[][] fijos = new boolean[9][9];
    protected int errores = 0;
    
    public int obtener(int f, int c) {
        return tablero[f][c];
    }

    public boolean esFijo(int f, int c) {
        return fijos[f][c];
    }

    public int getErrores() {
        return errores;
    }

    public void resetErrores() {
        errores = 0;
    }
    
    protected abstract void llenarTableroCompleto();
    public abstract boolean esValido(int f, int c, int v);
    public abstract void generar(int pistas);
    public abstract void limpiarEntradas();
    public abstract boolean isComplete();
}
