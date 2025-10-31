/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 *
 * @author ljmc2
 */
public class SudokuGUI extends JFrame {
    private static final int SIZE = 9;
    private final JTextField[][] cells = new JTextField[SIZE][SIZE];
    private SudokuLogica sudoku = new SudokuLogica();

    private JLabel lblErroresValor;
    private JButton btnValidar;
    private JButton btnRendirse;
    private JButton btnVaciar;

    public SudokuGUI(int pistas) {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(createGridPanel(), BorderLayout.CENTER);
        mainPanel.add(createUtilityPanel(), BorderLayout.EAST);

        add(createTopBar(), BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);

        //arranca segun opcion de menu principal
        generarSudoku(pistas);
    }

    private JComponent createTopBar() {
        JPanel top = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Sudoku", SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        top.add(title, BorderLayout.CENTER);
        return top;
    }

    private JComponent createGridPanel() {
        JPanel gridWrapper = new JPanel(new GridBagLayout());
        gridWrapper.setBackground(Color.WHITE);

        JPanel grid = new JPanel(new GridLayout(SIZE, SIZE));
        grid.setPreferredSize(new Dimension(540, 540));

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                cell.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
                cell.setBorder(computeCellBorder(r, c));

                final int row = r;
                final int col = c;

                // Acción con Enter
                cell.addActionListener(e -> {
                    String text = cell.getText().trim();
                    if (text.isEmpty()) return;
                    try {
                        int val = Integer.parseInt(text);
                        if (val < 1 || val > 9) {
                            JOptionPane.showMessageDialog(this, "Solo números del 1 al 9.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            cell.setText("");
                            return;
                        }
                        if (sudoku.asignar(row, col, val)) {
                            cell.setForeground(Color.BLUE);
                            cell.setEditable(false);//bloquear celdas correctas
                            if (sudoku.isComplete()) {
                                JOptionPane.showMessageDialog(this, "Sudoku completado. Crea uno nuevo para seguir jugando.", "Completado", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            cell.setForeground(Color.RED);
                        }
                        actualizarErrores();
                        verificarDerrota();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Entrada no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        cell.setText("");
                    }
                });

                cells[r][c] = cell;
                grid.add(cell);
            }
        }

        gridWrapper.add(grid);
        return gridWrapper;
    }

    private Border computeCellBorder(int row, int col) {
        int top = (row % 3 == 0) ? 3 : 1;
        int left = (col % 3 == 0) ? 3 : 1;
        int bottom = (row == SIZE - 1) ? 3 : 1;
        int right = (col == SIZE - 1) ? 3 : 1;
        return new MatteBorder(top, left, bottom, right, Color.DARK_GRAY);
    }

    private JComponent createUtilityPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);

        btnVaciar = new JButton("Vaciar");
        btnValidar = new JButton("Validar");
        btnRendirse = new JButton("Rendirse");

        JButton[] utilityButtons = {btnVaciar, btnValidar, btnRendirse};

        for (JButton b : utilityButtons) {
            b.setFont(buttonFont);
            b.setFocusable(false);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(120, 40));
            b.setPreferredSize(new Dimension(120, 40));
            rightPanel.add(b);
            rightPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        }

        // Vaciar tablero
        btnVaciar.addActionListener(e -> {
            sudoku.limpiarEntradas();
            actualizarTablero();
        });

        // Validar entradas
        btnValidar.addActionListener(e -> {
            for (int f = 0; f < SIZE; f++) {
                for (int c = 0; c < SIZE; c++) {
                    JTextField cell = cells[f][c];
                    if (!cell.isEditable()) continue;

                    String text = cell.getText().trim();
                    if (text.isEmpty()) continue;

                    try {
                        int val = Integer.parseInt(text);
                        if (val < 1 || val > 9) {
                            JOptionPane.showMessageDialog(this, "Solo números del 1 al 9.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            cell.setText("");
                            continue;
                        }
                        if (sudoku.asignar(f, c, val)) {
                            cell.setForeground(Color.BLUE);
                            cell.setEditable(false);
                        } else {
                            cell.setForeground(Color.RED);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Entrada no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        cell.setText("");
                    }
                }
            }
            actualizarErrores();
            verificarDerrota();
        });

        // Rendirse
        btnRendirse.addActionListener(e -> mostrarSolucion());

        // Contador de errores
        JLabel lblErroresTitulo = new JLabel("Errores:");
        lblErroresTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblErroresTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblErroresValor = new JLabel("0/10");
        lblErroresValor.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        lblErroresValor.setForeground(Color.RED);
        lblErroresValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.add(lblErroresTitulo);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(lblErroresValor);

        return rightPanel;
    }

    private JComponent createBottomPanel() {
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        JLabel label = new JLabel("Crear Sudoku Nuevo", SwingConstants.LEFT);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 50));

        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        JButton btnFacil = new JButton("Fácil");
        JButton btnMedio = new JButton("Medio");
        JButton btnDificil = new JButton("Difícil");

        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        JButton[] diffButtons = {btnFacil, btnMedio, btnDificil};

        for (JButton b : diffButtons) {
            b.setFont(buttonFont);
            b.setFocusable(false);
            b.setPreferredSize(new Dimension(110, 35));
            difficultyPanel.add(b);
        }

        // Eventos de generación
        btnFacil.addActionListener(e -> activarYGenerar(40));
        btnMedio.addActionListener(e -> activarYGenerar(30));
        btnDificil.addActionListener(e -> activarYGenerar(20));

        bottomWrapper.add(separator);
        bottomWrapper.add(label);
        bottomWrapper.add(difficultyPanel);

        return bottomWrapper;
    }

    private void activarYGenerar(int pistas) {
        btnValidar.setEnabled(true);
        btnRendirse.setEnabled(true);
        generarSudoku(pistas);
    }

    private void generarSudoku(int pistas) {
        sudoku.generar(pistas);
        actualizarTablero();
        actualizarErrores();
    }

    private void actualizarTablero() {
        for (int f = 0; f < SIZE; f++) {
            for (int c = 0; c < SIZE; c++) {
                int valor = sudoku.obtener(f, c);
                JTextField cell = cells[f][c];
                if (valor == 0) {
                    cell.setText("");
                    cell.setEditable(true);
                    cell.setForeground(Color.BLACK);
                } else {
                    cell.setText(String.valueOf(valor));
                    cell.setEditable(false);
                    cell.setForeground(Color.BLUE);
                }
            }
        }
    }

    private void actualizarErrores() {
        lblErroresValor.setText(sudoku.getErrores() + "/10");
    }

    private void verificarDerrota() {
        if (sudoku.getErrores() >= 10) {
            JOptionPane.showMessageDialog(this,
                    "Has agotado tus 10 errores. Has perdido el juego. Crea un sudoku nuevo para seguir jugando.",
                    "Derrota",
                    JOptionPane.ERROR_MESSAGE);
            mostrarSolucion();
        }
    }

    private void mostrarSolucion() {
        JOptionPane.showMessageDialog(this,
                "Has perdido.\nAquí tienes la solución del sudoku. Crea uno nuevo para seguir jugando.",
                "Juego terminado",
                JOptionPane.INFORMATION_MESSAGE);

        int[][] solucion = sudoku.getSolucion();
        for (int f = 0; f < SIZE; f++) {
            for (int c = 0; c < SIZE; c++) {
                cells[f][c].setText(String.valueOf(solucion[f][c]));
                cells[f][c].setEditable(false);
                cells[f][c].setForeground(new Color(0, 70, 180));
            }
        }

        btnVaciar.setEnabled(false);
        btnValidar.setEnabled(false);
        btnRendirse.setEnabled(false);
    }    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            SudokuGUI gui = new SudokuGUI(30);
            gui.setVisible(true);
        });
}
}
