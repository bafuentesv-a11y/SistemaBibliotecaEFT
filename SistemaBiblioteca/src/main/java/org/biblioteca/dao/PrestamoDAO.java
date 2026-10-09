package org.biblioteca.dao;

import org.biblioteca.database.DatabaseConnection;
import org.biblioteca.model.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    private final Connection connection;

    public PrestamoDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    // CREATE
    public boolean agregar(Prestamo prestamo) {

        String sql = "INSERT INTO prestamos " +
                "(id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, prestamo.getIdEstudiante());
            statement.setInt(2, prestamo.getIdLibro());
            statement.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(
                    4,
                    prestamo.getFechaDevolucion() != null
                            ? Date.valueOf(prestamo.getFechaDevolucion())
                            : null
            );
            statement.setBoolean(5, prestamo.isDevuelto());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar préstamo.");
            e.printStackTrace();
            return false;
        }
    }

    // READ - obtener todos
    public List<Prestamo> listar() {

        List<Prestamo> prestamos = new ArrayList<>();

        String sql = "SELECT id, id_estudiante, id_libro, " +
                "fecha_prestamo, fecha_devolucion, devuelto " +
                "FROM prestamos";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Prestamo prestamo = new Prestamo();

                prestamo.setId(resultSet.getInt("id"));
                prestamo.setIdEstudiante(resultSet.getInt("id_estudiante"));
                prestamo.setIdLibro(resultSet.getInt("id_libro"));

                Date fechaPrestamo = resultSet.getDate("fecha_prestamo");
                Date fechaDevolucion = resultSet.getDate("fecha_devolucion");

                if (fechaPrestamo != null) {
                    prestamo.setFechaPrestamo(fechaPrestamo.toLocalDate());
                }

                if (fechaDevolucion != null) {
                    prestamo.setFechaDevolucion(fechaDevolucion.toLocalDate());
                }

                prestamo.setDevuelto(resultSet.getBoolean("devuelto"));

                prestamos.add(prestamo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar préstamos.");
            e.printStackTrace();
        }

        return prestamos;
    }

    // READ - obtener uno por ID
    public Prestamo buscarPorId(int id) {

        String sql = "SELECT id, id_estudiante, id_libro, " +
                "fecha_prestamo, fecha_devolucion, devuelto " +
                "FROM prestamos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Prestamo prestamo = new Prestamo();

                    prestamo.setId(resultSet.getInt("id"));
                    prestamo.setIdEstudiante(resultSet.getInt("id_estudiante"));
                    prestamo.setIdLibro(resultSet.getInt("id_libro"));

                    Date fechaPrestamo = resultSet.getDate("fecha_prestamo");
                    Date fechaDevolucion = resultSet.getDate("fecha_devolucion");

                    if (fechaPrestamo != null) {
                        prestamo.setFechaPrestamo(fechaPrestamo.toLocalDate());
                    }

                    if (fechaDevolucion != null) {
                        prestamo.setFechaDevolucion(fechaDevolucion.toLocalDate());
                    }

                    prestamo.setDevuelto(resultSet.getBoolean("devuelto"));

                    return prestamo;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar préstamo.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean actualizar(Prestamo prestamo) {

        String sql = "UPDATE prestamos SET " +
                "id_estudiante = ?, id_libro = ?, fecha_prestamo = ?, " +
                "fecha_devolucion = ?, devuelto = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, prestamo.getIdEstudiante());
            statement.setInt(2, prestamo.getIdLibro());
            statement.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(4, Date.valueOf(prestamo.getFechaDevolucion()));
            statement.setBoolean(5, prestamo.isDevuelto());
            statement.setInt(6, prestamo.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar préstamo.");
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {

        String sql = "DELETE FROM prestamos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar préstamo.");
            e.printStackTrace();
            return false;
        }
    }

    // Registrar devolución
    public boolean registrarDevolucion(int id) {

        String sqlBuscar = "SELECT id_libro, devuelto FROM prestamos WHERE id = ?";
        String sqlPrestamo = "UPDATE prestamos SET devuelto = true, fecha_devolucion = CURDATE() WHERE id = ?";
        String sqlStock = "UPDATE libros SET stock = stock + 1 WHERE id = ?";

        boolean autoCommitOriginal = true;

        try {
            autoCommitOriginal = connection.getAutoCommit();
            connection.setAutoCommit(false);

            int idLibro;
            boolean yaDevuelto;

            try (PreparedStatement statement = connection.prepareStatement(sqlBuscar)) {

                statement.setInt(1, id);

                try (ResultSet resultado = statement.executeQuery()) {

                    if (!resultado.next()) {
                        connection.rollback();
                        return false;
                    }

                    idLibro = resultado.getInt("id_libro");
                    yaDevuelto = resultado.getBoolean("devuelto");
                }
            }

            if (yaDevuelto) {
                connection.rollback();
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sqlPrestamo)) {

                statement.setInt(1, id);

                if (statement.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            try (PreparedStatement statement = connection.prepareStatement(sqlStock)) {

                statement.setInt(1, idLibro);

                if (statement.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }

            System.out.println("Error al registrar devolución.");
            e.printStackTrace();

            return false;

        } finally {

            try {
                connection.setAutoCommit(autoCommitOriginal);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}