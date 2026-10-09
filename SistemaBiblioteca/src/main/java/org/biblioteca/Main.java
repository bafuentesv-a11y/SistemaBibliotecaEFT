package org.biblioteca;

import org.biblioteca.database.DatabaseConnection;
import org.biblioteca.controller.LoginController;
import org.biblioteca.view.LoginView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        DatabaseConnection.getInstance();

        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
        });
    }
}