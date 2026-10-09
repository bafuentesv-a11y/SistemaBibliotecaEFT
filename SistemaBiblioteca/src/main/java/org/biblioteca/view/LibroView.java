package org.biblioteca.view;

import javax.swing.*;

public class LibroView extends JFrame {

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtStock;
    private JTextField txtIdCategoria;

    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnVolver;

    private JTable tablaLibros;

    public LibroView() {

        setTitle("Gestión de Libros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 30, 100, 25);
        panel.add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(130, 30, 200, 25);
        panel.add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(30, 70, 100, 25);
        panel.add(lblAutor);

        txtAutor = new JTextField();
        txtAutor.setBounds(130, 70, 200, 25);
        panel.add(txtAutor);

        JLabel lblIsbn = new JLabel("ISBN:");
        lblIsbn.setBounds(30, 110, 100, 25);
        panel.add(lblIsbn);

        txtIsbn = new JTextField();
        txtIsbn.setBounds(130, 110, 200, 25);
        panel.add(txtIsbn);

        JLabel lblEditorial = new JLabel("Editorial:");
        lblEditorial.setBounds(30, 150, 100, 25);
        panel.add(lblEditorial);

        txtEditorial = new JTextField();
        txtEditorial.setBounds(130, 150, 200, 25);
        panel.add(txtEditorial);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(30, 190, 100, 25);
        panel.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(130, 190, 200, 25);
        panel.add(txtStock);

        JLabel lblCategoria = new JLabel("ID Categoría:");
        lblCategoria.setBounds(30, 230, 100, 25);
        panel.add(lblCategoria);

        txtIdCategoria = new JTextField();
        txtIdCategoria.setBounds(130, 230, 200, 25);
        panel.add(txtIdCategoria);

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
                "ID", "Título", "Autor", "ISBN",
                "Editorial", "Stock", "Categoría"
        };

        tablaLibros = new JTable(new Object[0][7], columnas);

        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBounds(30, 290, 720, 200);
        panel.add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(320, 510, 120, 30);
        panel.add(btnVolver);

        add(panel);
    }

    public JTextField getTxtTitulo() {
        return txtTitulo;
    }

    public JTextField getTxtAutor() {
        return txtAutor;
    }

    public JTextField getTxtIsbn() {
        return txtIsbn;
    }

    public JTextField getTxtEditorial() {
        return txtEditorial;
    }

    public JTextField getTxtStock() {
        return txtStock;
    }

    public JTextField getTxtIdCategoria() {
        return txtIdCategoria;
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

    public JTable getTablaLibros() {
        return tablaLibros;
    }
}