package Sudoku;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author marye
 */
public class MenuPrincipal {

    public MenuPrincipal() {
        inticomponent();
    }

    public void inticomponent() {
        JFrame VMenuPrinicipal = new JFrame("Menu Principal");
        VMenuPrinicipal.setSize(1200, 800);
        VMenuPrinicipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        VMenuPrinicipal.setResizable(false);
        VMenuPrinicipal.setLayout(new BorderLayout());
        VMenuPrinicipal.setLocationRelativeTo(null);

        JPanel PMenuPrinicipal = new JPanel(new BorderLayout());

        JLabel lblTitulo = new JLabel("SUDOKU", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
       lblTitulo.setBounds(100,200,100,200);
        PMenuPrinicipal.add(lblTitulo);

        JPanel PBotones = new JPanel(new GridLayout(3, 1, 0, 50));
        PBotones.setBorder(BorderFactory.createEmptyBorder(250, 250, 50, 250));

        JButton BtnFacil = new JButton("FACIL ");
        JButton BtnIntermedio = new JButton("INTERMEDIO");
        JButton BtnDificil = new JButton("DIFICIL");

        JButton[] botones = {BtnFacil, BtnIntermedio, BtnDificil};

        Font fuente = new Font("Segoe UI", Font.BOLD, 30);
        Color fondo = new Color(100, 149, 237);
        Color colorTexto = Color.WHITE;

        for (JButton b : botones) {
            b.setFont(fuente);
            b.setBackground(fondo);
            b.setForeground(colorTexto);
            b.setFocusPainted(false);

            PBotones.add(b);
        }

        BtnFacil.addActionListener(e -> {
            new SudokuGUI(45);
            VMenuPrinicipal.dispose();
        });

        BtnIntermedio.addActionListener(e -> {
            new SudokuGUI(30);
            VMenuPrinicipal.dispose();
        });

        BtnDificil.addActionListener(e -> {
            new SudokuGUI(20);
            VMenuPrinicipal.dispose();
        });
        PMenuPrinicipal.add(PBotones, BorderLayout.CENTER);
        VMenuPrinicipal.add(PMenuPrinicipal);
        VMenuPrinicipal.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
