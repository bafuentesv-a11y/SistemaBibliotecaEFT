package org.biblioteca.dao;

import org.biblioteca.database.DatabaseConnection;
import org.biblioteca.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final Connection connection;

    public CategoriaDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    // CREATE
    public boolean agregar(Categoria categoria) {

        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoria.getNombre());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar categoría.");
            e.printStackTrace();
            return false;
        }
    }

    // READ - obtener todas
    public List<Categoria> listar() {

        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT id, nombre FROM categorias";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Categoria categoria = new Categoria();

                categoria.setId(resultSet.getInt("id"));
                categoria.setNombre(resultSet.getString("nombre"));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías.");
            e.printStackTrace();
        }

        return categorias;
    }

    // READ - obtener una por ID
    public Categoria buscarPorId(int id) {

        String sql = "SELECT id, nombre FROM categorias WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Categoria categoria = new Categoria();

                    categoria.setId(resultSet.getInt("id"));
                    categoria.setNombre(resultSet.getString("nombre"));

                    return categoria;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar categoría.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean actualizar(Categoria categoria) {

        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoria.getNombre());
            statement.setInt(2, categoria.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría.");
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {

        String sql = "DELETE FROM categorias WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría.");
            e.printStackTrace();
            return false;
        }
    }
}