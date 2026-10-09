package org.biblioteca.view;

import javax.swing.*;

public class EstudianteView extends JFrame {

    private JTextField txtNombre;
    private JTextField txtRut;
    private JTextField txtCurso;
    private JTextField txtCorreo;

    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnVolver;

    private JTable tablaEstudiantes;

    public EstudianteView() {

        setTitle("Gestión de Estudiantes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 30, 200, 25);
        panel.add(txtNombre);

        JLabel lblRut = new JLabel("RUT:");
        lblRut.setBounds(30, 70, 100, 25);
        panel.add(lblRut);

        txtRut = new JTextField();
        txtRut.setBounds(130, 70, 200, 25);
        panel.add(txtRut);

        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setBounds(30, 110, 100, 25);
        panel.add(lblCurso);

        txtCurso = new JTextField();
        txtCurso.setBounds(130, 110, 200, 25);
        panel.add(txtCurso);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 150, 100, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(130, 150, 200, 25);
        panel.add(txtCorreo);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(370, 30, 120, 30);
        panel.add(btnAgregar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(500, 30, 120, 30);
        panel.add(btnBuscar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(370, 70, 120, 30);
        panel.add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(500, 70, 120, 30);
        panel.add(btnEliminar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(370, 110, 250, 30);
        panel.add(btnListar);

        String[] columnas = {
                "ID", "Nombre", "RUT", "Curso", "Correo"
        };

        tablaEstudiantes =
                new JTable(new Object[0][5], columnas);

        JScrollPane scrollPane =
                new JScrollPane(tablaEstudiantes);

        scrollPane.setBounds(30, 220, 720, 250);
        panel.add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(320, 500, 120, 30);
        panel.add(btnVolver);

        add(panel);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtRut() {
        return txtRut;
    }

    public JTextField getTxtCurso() {
        return txtCurso;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JTable getTablaEstudiantes() {
        return tablaEstudiantes;
    }
}