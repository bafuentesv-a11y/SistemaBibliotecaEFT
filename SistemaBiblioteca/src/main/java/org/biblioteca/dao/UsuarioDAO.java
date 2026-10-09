package org.biblioteca.dao;

import org.biblioteca.database.DatabaseConnection;
import org.biblioteca.model.Bibliotecario;
import org.biblioteca.model.Estudiante;
import org.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private final Connection connection;

    public UsuarioDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public Usuario autenticar(String correo, String contrasena) {

        String sql = "SELECT id, nombre, rut, correo, contraseña, rol " +
                "FROM usuarios " +
                "WHERE correo = ? AND contraseña = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, correo);
            statement.setString(2, contrasena);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String rut = resultSet.getString("rut");
                    String correoUsuario = resultSet.getString("correo");
                    String rol = resultSet.getString("rol");

                    if ("bibliotecario".equalsIgnoreCase(rol)) {

                        return new Bibliotecario(
                                id,
                                nombre,
                                rut,
                                correoUsuario
                        );

                    } else if ("estudiante".equalsIgnoreCase(rol)) {

                        return new Estudiante(
                                id,
                                nombre,
                                rut,
                                correoUsuario,
                                ""
                        );
                    }
                }

            }

        } catch (SQLException e) {
            System.out.println("Error al autenticar usuario.");
            e.printStackTrace();
        }

        return null;
    }
}