package org.biblioteca.controller;

import org.biblioteca.dao.LibroDAO;
import org.biblioteca.model.Libro;
import org.biblioteca.view.LibroView;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LibroController {

    private final LibroDAO libroDAO;
    private final LibroView libroView;

    public LibroController(LibroView libroView) {
        this.libroView = libroView;
        this.libroDAO = new LibroDAO();

        iniciarEventos();
    }

    private void iniciarEventos() {

        libroView.getBtnAgregar().addActionListener(e -> agregarLibroDesdeVista());

        libroView.getBtnBuscar().addActionListener(e -> buscarLibroDesdeVista());

        libroView.getBtnActualizar().addActionListener(e -> actualizarLibroDesdeVista());

        libroView.getBtnEliminar().addActionListener(e -> eliminarLibroDesdeVista());

        libroView.getBtnListar().addActionListener(e -> listarLibrosEnTabla());

        libroView.getBtnVolver().addActionListener(e -> libroView.dispose());
    }

    private void agregarLibroDesdeVista() {

        try {

            String titulo = libroView.getTxtTitulo().getText();
            String autor = libroView.getTxtAutor().getText();
            String isbn = libroView.getTxtIsbn().getText();
            String editorial = libroView.getTxtEditorial().getText();

            int stock = Integer.parseInt(
                    libroView.getTxtStock().getText()
            );

            int idCategoria = Integer.parseInt(
                    libroView.getTxtIdCategoria().getText()
            );

            Libro libro = new Libro(
                    0,
                    titulo,
                    autor,
                    isbn,
                    editorial,
                    stock,
                    idCategoria
            );

            if (libroDAO.agregar(libro)) {

                JOptionPane.showMessageDialog(
                        libroView,
                        "Libro agregado correctamente."
                );

                limpiarCampos();
                listarLibrosEnTabla();

            } else {

                JOptionPane.showMessageDialog(
                        libroView,
                        "No fue posible agregar el libro."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    libroView,
                    "Stock e ID de categoría deben ser números."
            );
        }
    }

    private void buscarLibroDesdeVista() {

        try {

            String entrada = JOptionPane.showInputDialog(
                    libroView,
                    "Ingrese el ID del libro:"
            );

            if (entrada == null) {
                return;
            }

            int id = Integer.parseInt(entrada);

            Libro libro = libroDAO.buscarPorId(id);

            if (libro != null) {

                libroView.getTxtTitulo().setText(libro.getTitulo());
                libroView.getTxtAutor().setText(libro.getAutor());
                libroView.getTxtIsbn().setText(libro.getIsbn());
                libroView.getTxtEditorial().setText(libro.getEditorial());

                libroView.getTxtStock().setText(
                        String.valueOf(libro.getStock())
                );

                libroView.getTxtIdCategoria().setText(
                        String.valueOf(libro.getIdCategoria())
                );

                JOptionPane.showMessageDialog(
                        libroView,
                        "Libro encontrado."
                );

            } else {

                JOptionPane.showMessageDialog(
                        libroView,
                        "No se encontró el libro."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    libroView,
                    "Debe ingresar un ID válido."
            );
        }
    }

    private void actualizarLibroDesdeVista() {

        try {

            String entrada = JOptionPane.showInputDialog(
                    libroView,
                    "Ingrese el ID del libro a actualizar:"
            );

            if (entrada == null) {
                return;
            }

            int id = Integer.parseInt(entrada);

            String titulo = libroView.getTxtTitulo().getText();
            String autor = libroView.getTxtAutor().getText();
            String isbn = libroView.getTxtIsbn().getText();
            String editorial = libroView.getTxtEditorial().getText();

            int stock = Integer.parseInt(
                    libroView.getTxtStock().getText()
            );

            int idCategoria = Integer.parseInt(
                    libroView.getTxtIdCategoria().getText()
            );

            Libro libro = new Libro(
                    id,
                    titulo,
                    autor,
                    isbn,
                    editorial,
                    stock,
                    idCategoria
            );

            if (libroDAO.actualizar(libro)) {

                JOptionPane.showMessageDialog(
                        libroView,
                        "Libro actualizado correctamente."
                );

                limpiarCampos();
                listarLibrosEnTabla();

            } else {

                JOptionPane.showMessageDialog(
                        libroView,
                        "No fue posible actualizar el libro."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    libroView,
                    "Los datos ingresados no son válidos."
            );
        }
    }

    private void eliminarLibroDesdeVista() {

        try {

            String entrada = JOptionPane.showInputDialog(
                    libroView,
                    "Ingrese el ID del libro a eliminar:"
            );

            if (entrada == null) {
                return;
            }

            int id = Integer.parseInt(entrada);

            int confirmacion = JOptionPane.showConfirmDialog(
                    libroView,
                    "¿Está seguro de eliminar este libro?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                if (libroDAO.eliminar(id)) {

                    JOptionPane.showMessageDialog(
                            libroView,
                            "Libro eliminado correctamente."
                    );

                    limpiarCampos();
                    listarLibrosEnTabla();

                } else {

                    JOptionPane.showMessageDialog(
                            libroView,
                            "No fue posible eliminar el libro."
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    libroView,
                    "Debe ingresar un ID válido."
            );
        }
    }

    private void listarLibrosEnTabla() {

        List<Libro> libros = libroDAO.listar();

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Título");
        modelo.addColumn("Autor");
        modelo.addColumn("ISBN");
        modelo.addColumn("Editorial");
        modelo.addColumn("Stock");
        modelo.addColumn("Categoría");

        for (Libro libro : libros) {

            modelo.addRow(new Object[]{
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getIsbn(),
                    libro.getEditorial(),
                    libro.getStock(),
                    libro.getIdCategoria()
            });
        }

        libroView.getTablaLibros().setModel(modelo);
    }

    private void limpiarCampos() {

        libroView.getTxtTitulo().setText("");
        libroView.getTxtAutor().setText("");
        libroView.getTxtIsbn().setText("");
        libroView.getTxtEditorial().setText("");
        libroView.getTxtStock().setText("");
        libroView.getTxtIdCategoria().setText("");
    }

    public boolean agregarLibro(Libro libro) {
        return libroDAO.agregar(libro);
    }

    public List<Libro> listarLibros() {
        return libroDAO.listar();
    }

    public Libro buscarLibro(int id) {
        return libroDAO.buscarPorId(id);
    }

    public boolean actualizarLibro(Libro libro) {
        return libroDAO.actualizar(libro);
    }

    public boolean eliminarLibro(int id) {
        return libroDAO.eliminar(id);
    }
}