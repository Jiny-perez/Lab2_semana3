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
    SudokuLogica sudoku=new SudokuLogica();
    private static final int SIZE = 9;
    private final JTextField[][] cells = new JTextField[SIZE][SIZE];

    public SudokuGUI() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        //panel principal; cuadricula y botones de utilidad
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(createGridPanel(), BorderLayout.CENTER);
        mainPanel.add(createUtilityPanel(), BorderLayout.EAST);

        add(createTopBar(), BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
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

        JButton btnVaciar = new JButton("Vaciar");
        JButton btnValidar = new JButton("Validar");
        JButton btnRendirse = new JButton("Rendirse");

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

        //contador de errores
        JLabel lblErroresTitulo = new JLabel("Errores:");
        lblErroresTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblErroresTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblErroresValor = new JLabel(String.valueOf(sudoku.getErrores()));
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

        //linea para separar tablero de dificultades
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        JLabel label = new JLabel("Crear Sudoku Nuevo", SwingConstants.CENTER);
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

        bottomWrapper.add(separator);
        bottomWrapper.add(label);
        bottomWrapper.add(difficultyPanel);

        return bottomWrapper;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {}

        SwingUtilities.invokeLater(() -> {
            SudokuGUI gui = new SudokuGUI();
            gui.setVisible(true);
        });
    }
}
