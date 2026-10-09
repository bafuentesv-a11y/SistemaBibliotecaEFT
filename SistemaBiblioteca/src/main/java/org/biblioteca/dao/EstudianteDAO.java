package org.biblioteca.dao;

import org.biblioteca.database.DatabaseConnection;
import org.biblioteca.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    private final Connection connection;

    public EstudianteDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    // CREATE
    public boolean agregar(Estudiante estudiante) {

        String sql = "INSERT INTO estudiantes " +
                "(nombre, rut, curso, correo) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, estudiante.getNombre());
            statement.setString(2, estudiante.getRut());
            statement.setString(3, estudiante.getCurso());
            statement.setString(4, estudiante.getCorreo());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar estudiante.");
            e.printStackTrace();
            return false;
        }
    }

    // READ - obtener todos
    public List<Estudiante> listar() {

        List<Estudiante> estudiantes = new ArrayList<>();

        String sql = "SELECT id, nombre, rut, curso, correo FROM estudiantes";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Estudiante estudiante = new Estudiante();

                estudiante.setId(resultSet.getInt("id"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setRut(resultSet.getString("rut"));
                estudiante.setCurso(resultSet.getString("curso"));
                estudiante.setCorreo(resultSet.getString("correo"));

                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar estudiantes.");
            e.printStackTrace();
        }

        return estudiantes;
    }

    // READ - obtener uno por ID
    public Estudiante buscarPorId(int id) {

        String sql = "SELECT id, nombre, rut, curso, correo " +
                "FROM estudiantes WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Estudiante estudiante = new Estudiante();

                    estudiante.setId(resultSet.getInt("id"));
                    estudiante.setNombre(resultSet.getString("nombre"));
                    estudiante.setRut(resultSet.getString("rut"));
                    estudiante.setCurso(resultSet.getString("curso"));
                    estudiante.setCorreo(resultSet.getString("correo"));

                    return estudiante;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar estudiante.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean actualizar(Estudiante estudiante) {

        String sql = "UPDATE estudiantes SET " +
                "nombre = ?, rut = ?, curso = ?, correo = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, estudiante.getNombre());
            statement.setString(2, estudiante.getRut());
            statement.setString(3, estudiante.getCurso());
            statement.setString(4, estudiante.getCorreo());
            statement.setInt(5, estudiante.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante.");
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {

        String sql = "DELETE FROM estudiantes WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar estudiante.");
            e.printStackTrace();
            return false;
        }
    }
}