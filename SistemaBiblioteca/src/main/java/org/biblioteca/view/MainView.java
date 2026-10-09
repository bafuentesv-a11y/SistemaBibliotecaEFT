package org.biblioteca.view;

import javax.swing.*;

public class MainView extends JFrame {

    private JButton btnLibros;
    private JButton btnEstudiantes;
    private JButton btnPrestamos;
    private JButton btnReportes;
    private JButton btnCerrarSesion;

    public MainView() {

        setTitle("Sistema de Biblioteca");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("SISTEMA DE BIBLIOTECA");
        lblTitulo.setBounds(150, 30, 250, 30);
        panel.add(lblTitulo);

        btnLibros = new JButton("Libros");
        btnLibros.setBounds(150, 80, 200, 35);
        panel.add(btnLibros);

        btnEstudiantes = new JButton("Estudiantes");
        btnEstudiantes.setBounds(150, 125, 200, 35);
        panel.add(btnEstudiantes);

        btnPrestamos = new JButton("Préstamos / Devoluciones");
        btnPrestamos.setBounds(150, 170, 200, 35);
        panel.add(btnPrestamos);

        btnReportes = new JButton("Reportes");
        btnReportes.setBounds(150, 215, 200, 35);
        panel.add(btnReportes);

        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBounds(150, 260, 200, 35);
        panel.add(btnCerrarSesion);

        add(panel);
    }

    public JButton getBtnLibros() {
        return btnLibros;
    }

    public JButton getBtnEstudiantes() {
        return btnEstudiantes;
    }

    public JButton getBtnPrestamos() {
        return btnPrestamos;
    }

    public JButton getBtnReportes() {
        return btnReportes;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}