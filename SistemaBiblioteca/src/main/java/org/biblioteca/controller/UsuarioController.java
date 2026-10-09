package org.biblioteca.controller;

import org.biblioteca.dao.UsuarioDAO;
import org.biblioteca.model.Usuario;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public Usuario autenticar(String correo, String contrasena) {
        return usuarioDAO.autenticar(correo, contrasena);
    }
}