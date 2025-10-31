package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        PMenuPrinicipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel PBotones = new JPanel(new GridLayout(4, 1, 0, 50));
        PBotones.setBorder(BorderFactory.createEmptyBorder(50, 250, 50, 250));

        JButton BtnFacil = new JButton("FACIL ");
        JButton BtnIntermedio = new JButton("INTERMEDIO");
        JButton BtnDificil = new JButton("DIFICIL");
        JButton BtnSalir = new JButton("Salir");

        JButton[] botones = {BtnFacil, BtnIntermedio, BtnDificil, BtnSalir};

        Font fuente = new Font("Segoe UI", Font.BOLD, 30);
        Color fondo = new Color(100, 149, 237);
        Color colorTexto = Color.black;

        for (JButton b : botones) {
            b.setFont(fuente);
            b.setBackground(fondo);
            b.setForeground(colorTexto);
            b.setFocusPainted(false);
            
            PBotones.add(b);
        }

        BtnFacil.addActionListener(e -> {
            new SudokuGUI(45).setVisible(true);
            VMenuPrinicipal.dispose();
        });

        BtnIntermedio.addActionListener(e -> {
            new SudokuGUI(30).setVisible(true);
            VMenuPrinicipal.dispose();
        });

        BtnDificil.addActionListener(e -> {
            new SudokuGUI(20).setVisible(true);
            VMenuPrinicipal.dispose();
        });

        BtnSalir.addActionListener(e -> System.exit(0));

        PMenuPrinicipal.add(PBotones, BorderLayout.CENTER);
        VMenuPrinicipal.add(PMenuPrinicipal);
        VMenuPrinicipal.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
