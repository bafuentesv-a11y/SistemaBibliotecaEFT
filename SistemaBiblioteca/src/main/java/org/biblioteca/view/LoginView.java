package org.biblioteca.view;

import javax.swing.*;

public class LoginView extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    public LoginView() {

        setTitle("Sistema de Biblioteca - Inicio de Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 40, 100, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 40, 180, 25);
        panel.add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 80, 100, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(150, 80, 180, 25);
        panel.add(txtContrasena);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(135, 130, 120, 30);
        panel.add(btnIngresar);

        add(panel);
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }
}