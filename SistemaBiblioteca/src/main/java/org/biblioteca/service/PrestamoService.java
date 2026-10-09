package org.biblioteca.service;

import org.biblioteca.dao.LibroDAO;
import org.biblioteca.model.Libro;
import org.biblioteca.model.Prestable;

public class PrestamoService {

    private final LibroDAO libroDAO;

    public PrestamoService() {
        libroDAO = new LibroDAO();
    }

    public synchronized boolean disminuirStock(int idLibro) {

        Libro libro = libroDAO.buscarPorId(idLibro);

        if (libro == null) {
            return false;
        }

        if (libro.getStock() <= 0) {
            return false;
        }

        Prestable prestable = libro;
        prestable.prestar();

        return libroDAO.actualizar(libro);
    }

    public synchronized boolean aumentarStock(int idLibro) {

        Libro libro = libroDAO.buscarPorId(idLibro);

        if (libro == null) {
            return false;
        }

        libro.setStock(libro.getStock() + 1);

        return libroDAO.actualizar(libro);
    }
}