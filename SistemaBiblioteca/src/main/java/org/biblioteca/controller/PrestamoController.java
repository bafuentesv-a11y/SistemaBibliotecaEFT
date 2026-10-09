package org.biblioteca.controller;

import org.biblioteca.dao.PrestamoDAO;
import org.biblioteca.model.Prestamo;
import org.biblioteca.service.PrestamoTask;
import org.biblioteca.view.PrestamoView;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class PrestamoController {

    private final PrestamoDAO prestamoDAO;
    private final PrestamoView prestamoView;

    public PrestamoController(PrestamoView prestamoView) {

        this.prestamoView = prestamoView;
        this.prestamoDAO = new PrestamoDAO();

        iniciarEventos();
    }

    private void iniciarEventos() {

        prestamoView.getBtnAgregar().addActionListener(
                e -> agregarPrestamoDesdeVista()
        );

        prestamoView.getBtnDevolver().addActionListener(
                e -> registrarDevolucionDesdeVista()
        );

        prestamoView.getBtnListar().addActionListener(
                e -> listarPrestamosEnTabla()
        );

        prestamoView.getBtnVolver().addActionListener(
                e -> prestamoView.dispose()
        );
    }

    private void agregarPrestamoDesdeVista() {

        try {

            int idEstudiante = Integer.parseInt(
                    prestamoView.getTxtIdEstudiante().getText()
            );

            int idLibro = Integer.parseInt(
                    prestamoView.getTxtIdLibro().getText()
            );

            LocalDate fechaPrestamo = LocalDate.parse(
                    prestamoView.getTxtFechaPrestamo().getText()
            );

            String fechaDevolucionTexto =
                    prestamoView.getTxtFechaDevolucion().getText();

            LocalDate fechaDevolucion = null;

            if (!fechaDevolucionTexto.isBlank()) {
                fechaDevolucion = LocalDate.parse(fechaDevolucionTexto);
            }

            Prestamo prestamo = new Prestamo(
                    0,
                    idEstudiante,
                    idLibro,
                    fechaPrestamo,
                    fechaDevolucion,
                    false
            );

            PrestamoTask task = new PrestamoTask(prestamo);
            Thread hilo = new Thread(task);
            hilo.start();

            JOptionPane.showMessageDialog(
                    prestamoView,
                    "El préstamo está siendo procesado."
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    prestamoView,
                    "Los ID de estudiante y libro deben ser números."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    prestamoView,
                    "La fecha debe tener el formato AAAA-MM-DD."
            );
        }
    }

    private void registrarDevolucionDesdeVista() {

        try {

            String entrada = JOptionPane.showInputDialog(
                    prestamoView,
                    "Ingrese el ID del préstamo:"
            );

            if (entrada == null) {
                return;
            }

            int id = Integer.parseInt(entrada);

            if (prestamoDAO.registrarDevolucion(id)) {

                JOptionPane.showMessageDialog(
                        prestamoView,
                        "Devolución registrada correctamente."
                );

                listarPrestamosEnTabla();

            } else {

                JOptionPane.showMessageDialog(
                        prestamoView,
                        "No fue posible registrar la devolución."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    prestamoView,
                    "Debe ingresar un ID válido."
            );
        }
    }

    private void listarPrestamosEnTabla() {

        List<Prestamo> prestamos = prestamoDAO.listar();

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("ID Estudiante");
        modelo.addColumn("ID Libro");
        modelo.addColumn("Fecha préstamo");
        modelo.addColumn("Vencimiento");
        modelo.addColumn("Fecha devolución");
        modelo.addColumn("Devuelto");
        modelo.addColumn("Estado");

        for (Prestamo prestamo : prestamos) {

            String estado;

            if (prestamo.isDevuelto()) {
                estado = "Devuelto";
            } else if (prestamo.estaAtrasado()) {
                estado = "ATRASADO";
            } else {
                estado = "Activo";
            }

            modelo.addRow(new Object[]{
                    prestamo.getId(),
                    prestamo.getIdEstudiante(),
                    prestamo.getIdLibro(),
                    prestamo.getFechaPrestamo(),
                    prestamo.calcularFechaVencimiento(),
                    prestamo.getFechaDevolucion(),
                    prestamo.isDevuelto(),
                    estado
            });
        }

        prestamoView.getTablaPrestamos().setModel(modelo);
    }

    private void limpiarCampos() {

        prestamoView.getTxtIdEstudiante().setText("");
        prestamoView.getTxtIdLibro().setText("");
        prestamoView.getTxtFechaPrestamo().setText("");
        prestamoView.getTxtFechaDevolucion().setText("");
    }

    public boolean agregarPrestamo(Prestamo prestamo) {
        return prestamoDAO.agregar(prestamo);
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoDAO.listar();
    }

    public Prestamo buscarPrestamo(int id) {
        return prestamoDAO.buscarPorId(id);
    }

    public boolean actualizarPrestamo(Prestamo prestamo) {
        return prestamoDAO.actualizar(prestamo);
    }

    public boolean eliminarPrestamo(int id) {
        return prestamoDAO.eliminar(id);
    }
}