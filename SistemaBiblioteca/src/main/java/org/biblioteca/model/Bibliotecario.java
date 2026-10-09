package org.biblioteca.model;

public class Bibliotecario extends Usuario {

    public Bibliotecario() {
        super();
    }

    public Bibliotecario(int id, String nombre, String rut, String correo) {
        super(id, nombre, rut, correo);
    }

    @Override
    public String getRol() {
        return "bibliotecario";
    }
}