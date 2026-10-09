package org.biblioteca.view;

import javax.swing.*;

public class ReporteView extends JFrame {

    private JTextArea txtReporte;

    private JButton btnLibrosMasPrestados;
    private JButton btnHistorialEstudiante;
    private JButton btnPrestamosActivos;
    private JButton btnVolver;

    public ReporteView() {

        setTitle("Reportes de Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Reportes del sistema");
        lblTitulo.setBounds(30, 20, 250, 30);
        panel.add(lblTitulo);

        btnLibrosMasPrestados = new JButton("Libros más prestados");
        btnLibrosMasPrestados.setBounds(30, 70, 220, 35);
        panel.add(btnLibrosMasPrestados);

        btnHistorialEstudiante = new JButton("Historial por estudiante");
        btnHistorialEstudiante.setBounds(270, 70, 220, 35);
        panel.add(btnHistorialEstudiante);

        btnPrestamosActivos = new JButton("Libros actualmente en préstamo");
        btnPrestamosActivos.setBounds(510, 70, 240, 35);
        panel.add(btnPrestamosActivos);

        txtReporte = new JTextArea();
        txtReporte.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtReporte);
        scrollPane.setBounds(30, 130, 720, 350);
        panel.add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(320, 500, 120, 30);
        panel.add(btnVolver);

        add(panel);
    }

    public JTextArea getTxtReporte() {
        return txtReporte;
    }

    public JButton getBtnLibrosMasPrestados() {
        return btnLibrosMasPrestados;
    }

    public JButton getBtnHistorialEstudiante() {
        return btnHistorialEstudiante;
    }

    public JButton getBtnPrestamosActivos() {
        return btnPrestamosActivos;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}