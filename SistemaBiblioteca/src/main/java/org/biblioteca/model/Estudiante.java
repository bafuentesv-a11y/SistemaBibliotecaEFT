package org.biblioteca.model;

public class Estudiante extends Usuario {

    private String curso;

    public Estudiante() {
        super();
    }

    public Estudiante(int id, String nombre, String rut, String correo, String curso) {
        super(id, nombre, rut, correo);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String getRol() {
        return "estudiante";
    }
}