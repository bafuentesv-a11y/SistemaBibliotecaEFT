package org.biblioteca.controller;

import org.biblioteca.model.Usuario;
import org.biblioteca.view.EstudianteView;
import org.biblioteca.view.LibroView;
import org.biblioteca.view.MainView;
import org.biblioteca.view.PrestamoView;
import org.biblioteca.view.ReporteView;

public class MainController {

    private final MainView mainView;
    private final Usuario usuario;

    public MainController(MainView mainView, Usuario usuario) {

        this.mainView = mainView;
        this.usuario = usuario;

        configurarAccesos();
        iniciarEventos();
    }

    private void configurarAccesos() {

        if (usuario.getRol().equals("estudiante")) {

            mainView.getBtnLibros().setEnabled(false);
            mainView.getBtnEstudiantes().setEnabled(false);

        } else if (usuario.getRol().equals("bibliotecario")) {

            mainView.getBtnLibros().setEnabled(true);
            mainView.getBtnEstudiantes().setEnabled(true);
        }
    }

    private void iniciarEventos() {

        mainView.getBtnLibros().addActionListener(
                e -> abrirLibros()
        );

        mainView.getBtnEstudiantes().addActionListener(
                e -> abrirEstudiantes()
        );

        mainView.getBtnPrestamos().addActionListener(
                e -> abrirPrestamos()
        );

        mainView.getBtnReportes().addActionListener(
                e -> abrirReportes()
        );

        mainView.getBtnCerrarSesion().addActionListener(
                e -> mainView.dispose()
        );
    }

    private void abrirLibros() {

        LibroView libroView = new LibroView();
        new LibroController(libroView);
        libroView.setVisible(true);
    }

    private void abrirEstudiantes() {

        EstudianteView estudianteView = new EstudianteView();
        new EstudianteController(estudianteView);
        estudianteView.setVisible(true);
    }

    private void abrirPrestamos() {

        PrestamoView prestamoView = new PrestamoView();
        new PrestamoController(prestamoView);
        prestamoView.setVisible(true);
    }

    private void abrirReportes() {

        ReporteView reporteView = new ReporteView();
        new ReporteController(reporteView);
        reporteView.setVisible(true);
    }
}