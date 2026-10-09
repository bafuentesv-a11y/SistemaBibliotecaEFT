package org.biblioteca.service;

import org.biblioteca.dao.PrestamoDAO;
import org.biblioteca.model.Prestamo;

public class PrestamoTask implements Runnable {

    private final PrestamoService prestamoService;
    private final PrestamoDAO prestamoDAO;
    private final Prestamo prestamo;

    public PrestamoTask(Prestamo prestamo) {
        this.prestamo = prestamo;
        this.prestamoService = new PrestamoService();
        this.prestamoDAO = new PrestamoDAO();
    }

    @Override
    public void run() {

        boolean stockActualizado =
                prestamoService.disminuirStock(prestamo.getIdLibro());

        if (!stockActualizado) {
            System.out.println("No se pudo realizar el préstamo. Stock no disponible.");
            return;
        }

        boolean prestamoRegistrado = prestamoDAO.agregar(prestamo);

        if (prestamoRegistrado) {
            System.out.println("Préstamo registrado correctamente.");
        } else {
            prestamoService.aumentarStock(prestamo.getIdLibro());
            System.out.println("No se pudo registrar el préstamo. Se restauró el stock.");
        }
    }
}