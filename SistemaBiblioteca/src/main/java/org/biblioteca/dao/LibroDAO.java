package org.biblioteca.dao;

import org.biblioteca.database.DatabaseConnection;
import org.biblioteca.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private final Connection connection;

    public LibroDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    // CREATE
    public boolean agregar(Libro libro) {

        String sql = "INSERT INTO libros " +
                "(titulo, autor, isbn, editorial, stock, id_categoria) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getIsbn());
            statement.setString(4, libro.getEditorial());
            statement.setInt(5, libro.getStock());
            statement.setInt(6, libro.getIdCategoria());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar libro.");
            e.printStackTrace();
            return false;
        }
    }

    // READ - obtener todos
    public List<Libro> listar() {

        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT id, titulo, autor, isbn, editorial, stock, id_categoria " +
                "FROM libros";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Libro libro = new Libro();

                libro.setId(resultSet.getInt("id"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setAutor(resultSet.getString("autor"));
                libro.setIsbn(resultSet.getString("isbn"));
                libro.setEditorial(resultSet.getString("editorial"));
                libro.setStock(resultSet.getInt("stock"));
                libro.setIdCategoria(resultSet.getInt("id_categoria"));

                libros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar libros.");
            e.printStackTrace();
        }

        return libros;
    }

    // READ - obtener uno por ID
    public Libro buscarPorId(int id) {

        String sql = "SELECT id, titulo, autor, isbn, editorial, stock, id_categoria " +
                "FROM libros WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Libro libro = new Libro();

                    libro.setId(resultSet.getInt("id"));
                    libro.setTitulo(resultSet.getString("titulo"));
                    libro.setAutor(resultSet.getString("autor"));
                    libro.setIsbn(resultSet.getString("isbn"));
                    libro.setEditorial(resultSet.getString("editorial"));
                    libro.setStock(resultSet.getInt("stock"));
                    libro.setIdCategoria(resultSet.getInt("id_categoria"));

                    return libro;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean actualizar(Libro libro) {

        String sql = "UPDATE libros SET " +
                "titulo = ?, autor = ?, isbn = ?, editorial = ?, " +
                "stock = ?, id_categoria = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getIsbn());
            statement.setString(4, libro.getEditorial());
            statement.setInt(5, libro.getStock());
            statement.setInt(6, libro.getIdCategoria());
            statement.setInt(7, libro.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar libro.");
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {

        String sql = "DELETE FROM libros WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar libro.");
            e.printStackTrace();
            return false;
        }
    }
}