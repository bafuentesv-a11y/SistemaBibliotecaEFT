package org.biblioteca.controller;

import org.biblioteca.dao.UsuarioDAO;
import org.biblioteca.model.Usuario;
import org.biblioteca.view.LoginView;
import org.biblioteca.view.MainView;
import org.biblioteca.controller.MainController;
import org.biblioteca.controller.MainController;

import javax.swing.*;

public class LoginController {

    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.usuarioDAO = new UsuarioDAO();

        iniciarEventos();
    }

    private void iniciarEventos() {
        loginView.getBtnIngresar().addActionListener(e -> autenticar());
    }

    private void autenticar() {

        String correo = loginView.getTxtCorreo().getText();

        String contrasena = new String(
                loginView.getTxtContrasena().getPassword()
        );

        if (correo.isBlank() || contrasena.isBlank()) {

            JOptionPane.showMessageDialog(
                    loginView,
                    "Debe ingresar correo y contraseña."
            );

            return;
        }

        Usuario usuario = usuarioDAO.autenticar(correo, contrasena);

        if (usuario != null) {

            JOptionPane.showMessageDialog(
                    loginView,
                    "Bienvenido/a " + usuario.getNombre()
            );

            loginView.dispose();

            MainView mainView = new MainView();
            new MainController(mainView, usuario);
            mainView.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    loginView,
                    "Correo o contraseña incorrectos."
            );
        }
    }
}