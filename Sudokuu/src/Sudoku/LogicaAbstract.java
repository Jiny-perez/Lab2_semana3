package Sudoku;

/**
 *
 * @author ljmc2
 */
public abstract class LogicaAbstract {
    protected int[][] tablero = new int[9][9];
    protected int[][] solucion = new int[9][9];
    protected boolean[][] predeterminado = new boolean[9][9];
    protected int errores=0;
    
    public int getErrores() {
        return errores;
    }

    public void resetErrores() {
        errores = 0;
    }

    public int[][] getSolucion() {
        return solucion;
    }
   
    public abstract int obtener(int row, int col);

    public abstract boolean isPredeterminado(int row, int col);

    public abstract boolean asignar(int row, int col, int val);

    public abstract boolean remover(int row, int col);

    public abstract boolean esValido(int row, int col, int val);

    public abstract void generar(int pistas);

    public abstract void limpiarEntradas();

    public abstract boolean isComplete();

    protected abstract void llenarTableroCompleto();
}
