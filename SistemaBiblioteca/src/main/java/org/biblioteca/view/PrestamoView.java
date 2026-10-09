package org.biblioteca.view;

import javax.swing.*;

public class PrestamoView extends JFrame {

    private JTextField txtIdEstudiante;
    private JTextField txtIdLibro;
    private JTextField txtFechaPrestamo;
    private JTextField txtFechaDevolucion;

    private JButton btnAgregar;
    private JButton btnDevolver;
    private JButton btnListar;
    private JButton btnVolver;

    private JTable tablaPrestamos;

    public PrestamoView() {

        setTitle("Gestión de Préstamos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblEstudiante = new JLabel("ID Estudiante:");
        lblEstudiante.setBounds(30, 30, 120, 25);
        panel.add(lblEstudiante);

        txtIdEstudiante = new JTextField();
        txtIdEstudiante.setBounds(150, 30, 200, 25);
        panel.add(txtIdEstudiante);

        JLabel lblLibro = new JLabel("ID Libro:");
        lblLibro.setBounds(30, 70, 120, 25);
        panel.add(lblLibro);

        txtIdLibro = new JTextField();
        txtIdLibro.setBounds(150, 70, 200, 25);
        panel.add(txtIdLibro);

        JLabel lblFechaPrestamo = new JLabel("Fecha préstamo:");
        lblFechaPrestamo.setBounds(30, 110, 120, 25);
        panel.add(lblFechaPrestamo);

        txtFechaPrestamo = new JTextField();
        txtFechaPrestamo.setBounds(150, 110, 200, 25);
        panel.add(txtFechaPrestamo);

        JLabel lblFechaDevolucion = new JLabel("Fecha devolución:");
        lblFechaDevolucion.setBounds(30, 150, 120, 25);
        panel.add(lblFechaDevolucion);

        txtFechaDevolucion = new JTextField();
        txtFechaDevolucion.setBounds(150, 150, 200, 25);
        panel.add(txtFechaDevolucion);

        btnAgregar = new JButton("Registrar préstamo");
        btnAgregar.setBounds(390, 30, 180, 30);
        panel.add(btnAgregar);

        btnDevolver = new JButton("Registrar devolución");
        btnDevolver.setBounds(390, 70, 180, 30);
        panel.add(btnDevolver);

        btnListar = new JButton("Listar préstamos");
        btnListar.setBounds(390, 110, 180, 30);
        panel.add(btnListar);

        String[] columnas = {
                "ID",
                "ID Estudiante",
                "ID Libro",
                "Fecha préstamo",
                "Fecha devolución",
                "Devuelto"
        };

        tablaPrestamos = new JTable(
                new Object[0][6],
                columnas
        );

        JScrollPane scrollPane = new JScrollPane(tablaPrestamos);
        scrollPane.setBounds(30, 210, 720, 250);
        panel.add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(320, 490, 120, 30);
        panel.add(btnVolver);

        add(panel);
    }

    public JTextField getTxtIdEstudiante() {
        return txtIdEstudiante;
    }

    public JTextField getTxtIdLibro() {
        return txtIdLibro;
    }

    public JTextField getTxtFechaPrestamo() {
        return txtFechaPrestamo;
    }

    public JTextField getTxtFechaDevolucion() {
        return txtFechaDevolucion;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnDevolver() {
        return btnDevolver;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JTable getTablaPrestamos() {
        return tablaPrestamos;
    }
}