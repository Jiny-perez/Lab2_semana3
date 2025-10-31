/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        Color azul = new Color(70, 130, 180);
        Color azulHover = new Color(100, 160, 210); // color al pasar el mouse
        Color colorTexto = Color.BLACK;

        JPanel PMenuPrinicipal = new JPanel(new BorderLayout());

        JLabel lblTitulo = new JLabel("SUDOKU", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        PMenuPrinicipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel PBotones = new JPanel(new GridLayout(4, 1, 0, 50));
        PBotones.setBorder(BorderFactory.createEmptyBorder(50, 250, 50, 250));

        JButton BtnFacil = new JButton("FÁCIL ");
        JButton BtnIntermedio = new JButton("INTERMEDIO");
        JButton BtnDificil = new JButton("DIFÍCIL");
        JButton BtnSalir = new JButton("Salir");

        JButton[] botones = {BtnFacil, BtnIntermedio, BtnDificil, BtnSalir};
        Font fuente = new Font("Segoe UI", Font.BOLD, 30);

        for (JButton b : botones) {
            b.setFont(fuente);
            b.setBackground(azul);
            b.setForeground(colorTexto);
            b.setFocusPainted(false);

            // efecto hover
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    b.setBackground(azulHover);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    b.setBackground(azul);
                }
            });
            PBotones.add(b);
        }

        BtnFacil.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new SudokuGUI(45).setVisible(true);
            VMenuPrinicipal.dispose();
        });

        BtnIntermedio.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new SudokuGUI(30).setVisible(true);
            VMenuPrinicipal.dispose();
        });

        BtnDificil.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new SudokuGUI(20).setVisible(true);
            VMenuPrinicipal.dispose();
        });

        BtnSalir.addActionListener(e -> System.exit(0));

        PMenuPrinicipal.add(PBotones, BorderLayout.CENTER);
        VMenuPrinicipal.add(PMenuPrinicipal);
        VMenuPrinicipal.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        new MenuPrincipal();
    }
}