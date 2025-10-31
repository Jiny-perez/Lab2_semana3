/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
