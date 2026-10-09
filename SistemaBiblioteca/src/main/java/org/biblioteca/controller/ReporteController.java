package org.biblioteca.controller;

import org.biblioteca.dao.LibroDAO;
import org.biblioteca.dao.PrestamoDAO;
import org.biblioteca.model.Libro;
import org.biblioteca.model.Prestamo;
import org.biblioteca.view.ReporteView;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteController {

    private final ReporteView reporteView;
    private final PrestamoDAO prestamoDAO;
    private final LibroDAO libroDAO;

    public ReporteController(ReporteView reporteView) {

        this.reporteView = reporteView;
        this.prestamoDAO = new PrestamoDAO();
        this.libroDAO = new LibroDAO();

        iniciarEventos();
    }

    private void iniciarEventos() {

        reporteView.getBtnLibrosMasPrestados()
                .addActionListener(e -> mostrarLibrosMasPrestados());

        reporteView.getBtnHistorialEstudiante()
                .addActionListener(e -> mostrarHistorialEstudiante());

        reporteView.getBtnPrestamosActivos()
                .addActionListener(e -> mostrarPrestamosActivos());

        reporteView.getBtnVolver()
                .addActionListener(e -> reporteView.dispose());
    }

    private void mostrarLibrosMasPrestados() {

        List<Prestamo> prestamos = prestamoDAO.listar();

        Map<Integer, Integer> cantidadPrestamos = new HashMap<>();

        for (Prestamo prestamo : prestamos) {

            int idLibro = prestamo.getIdLibro();

            cantidadPrestamos.put(
                    idLibro,
                    cantidadPrestamos.getOrDefault(idLibro, 0) + 1
            );
        }

        StringBuilder reporte = new StringBuilder();

        reporte.append("=== LIBROS MÁS PRESTADOS ===\n\n");

        for (Map.Entry<Integer, Integer> entrada : cantidadPrestamos.entrySet()) {

            Libro libro = libroDAO.buscarPorId(entrada.getKey());

            if (libro != null) {

                reporte.append("Libro: ")
                        .append(libro.getTitulo())
                        .append("\n");

                reporte.append("Autor: ")
                        .append(libro.getAutor())
                        .append("\n");

                reporte.append("Cantidad de préstamos: ")
                        .append(entrada.getValue())
                        .append("\n\n");
            }
        }

        if (cantidadPrestamos.isEmpty()) {
            reporte.append("No existen préstamos registrados.");
        }

        reporteView.getTxtReporte().setText(reporte.toString());
    }

    private void mostrarHistorialEstudiante() {

        String entrada = JOptionPane.showInputDialog(
                reporteView,
                "Ingrese el ID del estudiante:"
        );

        if (entrada == null) {
            return;
        }

        try {

            int idEstudiante = Integer.parseInt(entrada);

            List<Prestamo> prestamos = prestamoDAO.listar();

            StringBuilder reporte = new StringBuilder();

            reporte.append("=== HISTORIAL DEL ESTUDIANTE ===\n\n");
            reporte.append("ID estudiante: ")
                    .append(idEstudiante)
                    .append("\n\n");

            boolean encontrado = false;

            for (Prestamo prestamo : prestamos) {

                if (prestamo.getIdEstudiante() == idEstudiante) {

                    encontrado = true;

                    Libro libro = libroDAO.buscarPorId(
                            prestamo.getIdLibro()
                    );

                    reporte.append("Préstamo ID: ")
                            .append(prestamo.getId())
                            .append("\n");

                    if (libro != null) {

                        reporte.append("Libro: ")
                                .append(libro.getTitulo())
                                .append("\n");
                    }

                    reporte.append("Fecha préstamo: ")
                            .append(prestamo.getFechaPrestamo())
                            .append("\n");

                    reporte.append("Fecha devolución: ")
                            .append(prestamo.getFechaDevolucion())
                            .append("\n");

                    reporte.append("Devuelto: ")
                            .append(prestamo.isDevuelto())
                            .append("\n\n");
                }
            }

            if (!encontrado) {
                reporte.append(
                        "No existen préstamos para este estudiante."
                );
            }

            reporteView.getTxtReporte().setText(reporte.toString());

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    reporteView,
                    "Debe ingresar un ID válido."
            );
        }
    }

    private void mostrarPrestamosActivos() {

        List<Prestamo> prestamos = prestamoDAO.listar();

        StringBuilder reporte = new StringBuilder();

        reporte.append("=== LIBROS ACTUALMENTE EN PRÉSTAMO ===\n\n");

        boolean encontrado = false;

        for (Prestamo prestamo : prestamos) {

            if (!prestamo.isDevuelto()) {

                encontrado = true;

                Libro libro = libroDAO.buscarPorId(
                        prestamo.getIdLibro()
                );

                reporte.append("Préstamo ID: ")
                        .append(prestamo.getId())
                        .append("\n");

                reporte.append("ID estudiante: ")
                        .append(prestamo.getIdEstudiante())
                        .append("\n");

                if (libro != null) {

                    reporte.append("Libro: ")
                            .append(libro.getTitulo())
                            .append("\n");
                }

                reporte.append("Fecha préstamo: ")
                        .append(prestamo.getFechaPrestamo())
                        .append("\n\n");
            }
        }

        if (!encontrado) {
            reporte.append("No existen préstamos activos.");
        }

        reporteView.getTxtReporte().setText(reporte.toString());
    }
}