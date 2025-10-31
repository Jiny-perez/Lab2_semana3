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

    public abstract void limpiarEntradas();
    
}
