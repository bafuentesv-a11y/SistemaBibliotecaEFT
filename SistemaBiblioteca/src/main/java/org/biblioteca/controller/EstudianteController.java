package org.biblioteca.controller;

import org.biblioteca.dao.EstudianteDAO;
import org.biblioteca.model.Estudiante;
import org.biblioteca.view.EstudianteView;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EstudianteController {

    private final EstudianteDAO estudianteDAO;
    private final EstudianteView estudianteView;

    public EstudianteController(EstudianteView estudianteView) {

        this.estudianteView = estudianteView;
        this.estudianteDAO = new EstudianteDAO();

        iniciarEventos();
    }

    private void iniciarEventos() {

        estudianteView.getBtnAgregar().addActionListener(
                e -> agregarEstudianteDesdeVista()
        );

        estudianteView.getBtnBuscar().addActionListener(
                e -> buscarEstudianteDesdeVista()
        );

        estudianteView.getBtnActualizar().addActionListener(
                e -> actualizarEstudianteDesdeVista()
        );

        estudianteView.getBtnEliminar().addActionListener(
                e -> eliminarEstudianteDesdeVista()
        );

        estudianteView.getBtnListar().addActionListener(
                e -> listarEstudiantesEnTabla()
        );

        estudianteView.getBtnVolver().addActionListener(
                e -> estudianteView.dispose()
        );
    }

    private void agregarEstudianteDesdeVista() {

        String nombre = estudianteView.getTxtNombre().getText();
        String rut = estudianteView.getTxtRut().getText();
        String curso = estudianteView.getTxtCurso().getText();
        String correo = estudianteView.getTxtCorreo().getText();

        Estudiante estudiante = new Estudiante(
                0,
                nombre,
                rut,
                correo,
                curso
        );

        if (estudianteDAO.agregar(estudiante)) {

            JOptionPane.showMessageDialog(
                    estudianteView,
                    "Estudiante agregado correctamente."
            );

            limpiarCampos();
            listarEstudiantesEnTabla();

        } else {

            JOptionPane.showMessageDialog(
                    estudianteView,
                    "No fue posible agregar el estudiante."
            );
        }
    }

    private void buscarEstudianteDesdeVista() {

        try {

            String entrada = JOptionPane.showInputDialog(
                    estudianteView,
                    "Ingrese el ID del estudiante:"
            );

            if (entrada == null) {
                return;
            }

            int id = Integer.parseInt(entrada);

            Estudiante estudiante =
                    estudianteDAO.buscarPorId(id);

            if (estudiante != null) {

                estudianteView.getTxtNombre().setText(
                        estudiante.getNombre()
                );

                estudianteView.getTxtRut().setText(
                        estudiante.getRut()
                );

                estudianteView.getTxtCurso().setText(
                        estudiante.getCurso()
                );

                estudianteView.getTxtCorreo().setText(
                        estudiante.getCorreo()
                );

                JOptionPane.showMessageDialog(
                        estudianteView,
                        "Estudiante encontrado."
                );

            } else {

                JOptionPane.showMessageDialog(
                        estudianteView,
                        "No se encontró el estudiante."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    estudianteView,
                    "Debe ingresar un ID válido."
            );
        }
    }

    private void actualizarEstudianteDesdeVista() {

        try {

            String entrada = JOptionPane.showInputDialog(
                    estudianteView,
                    "Ingrese el ID del estudiante a actualizar:"
            );

            if (entrada == null) {
                return;
            }

            int id = Integer.parseInt(entrada);

            String nombre = estudianteView.getTxtNombre().getText();
            String rut = estudianteView.getTxtRut().getText();
            String curso = estudianteView.getTxtCurso().getText();
            String correo = estudianteView.getTxtCorreo().getText();

            Estudiante estudiante = new Estudiante(
                    id,
                    nombre,
                    rut,
                    correo,
                    curso
            );

            if (estudianteDAO.actualizar(estudiante)) {

                JOptionPane.showMessageDialog(
                        estudianteView,
                        "Estudiante actualizado correctamente."
                );

                limpiarCampos();
                listarEstudiantesEnTabla();

            } else {

                JOptionPane.showMessageDialog(
                        estudianteView,
                        "No fue posible actualizar el estudiante."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    estudianteView,
                    "Debe ingresar un ID válido."
            );
        }
    }

    private void eliminarEstudianteDesdeVista() {

        try {

            String entrada = JOptionPane.showInputDialog(
                    estudianteView,
                    "Ingrese el ID del estudiante a eliminar:"
            );

            if (entrada == null) {
                return;
            }

            int id = Integer.parseInt(entrada);

            int confirmacion = JOptionPane.showConfirmDialog(
                    estudianteView,
                    "¿Está seguro de eliminar este estudiante?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                if (estudianteDAO.eliminar(id)) {

                    JOptionPane.showMessageDialog(
                            estudianteView,
                            "Estudiante eliminado correctamente."
                    );

                    limpiarCampos();
                    listarEstudiantesEnTabla();

                } else {

                    JOptionPane.showMessageDialog(
                            estudianteView,
                            "No fue posible eliminar el estudiante."
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    estudianteView,
                    "Debe ingresar un ID válido."
            );
        }
    }

    private void listarEstudiantesEnTabla() {

        List<Estudiante> estudiantes =
                estudianteDAO.listar();

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("RUT");
        modelo.addColumn("Curso");
        modelo.addColumn("Correo");

        for (Estudiante estudiante : estudiantes) {

            modelo.addRow(new Object[]{
                    estudiante.getId(),
                    estudiante.getNombre(),
                    estudiante.getRut(),
                    estudiante.getCurso(),
                    estudiante.getCorreo()
            });
        }

        estudianteView.getTablaEstudiantes()
                .setModel(modelo);
    }

    private void limpiarCampos() {

        estudianteView.getTxtNombre().setText("");
        estudianteView.getTxtRut().setText("");
        estudianteView.getTxtCurso().setText("");
        estudianteView.getTxtCorreo().setText("");
    }
}