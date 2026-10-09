package org.biblioteca.controller;

import org.biblioteca.dao.CategoriaDAO;
import org.biblioteca.model.Categoria;

import java.util.List;

public class CategoriaController {

    private final CategoriaDAO categoriaDAO;

    public CategoriaController() {
        categoriaDAO = new CategoriaDAO();
    }

    public boolean agregarCategoria(Categoria categoria) {
        return categoriaDAO.agregar(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listar();
    }

    public Categoria buscarCategoria(int id) {
        return categoriaDAO.buscarPorId(id);
    }

    public boolean actualizarCategoria(Categoria categoria) {
        return categoriaDAO.actualizar(categoria);
    }

    public boolean eliminarCategoria(int id) {
        return categoriaDAO.eliminar(id);
    }
}