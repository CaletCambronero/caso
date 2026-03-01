package org.example.Repositorio;

import org.example.Modelo.Curso;
import org.example.misc.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoRepositorio implements IRepositorio<Curso> {

    @Override
    public void guardar(Curso curso) {
        String sql = "INSERT INTO cursos (id, nombre, descripcion, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, curso.getId());
            stmt.setString(2, curso.getNombre());
            stmt.setString(3, curso.getDescripcion());
            stmt.setString(4, curso.getEstado());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar curso: " + e.getMessage());
        }
    }

    @Override
    public Optional<Curso> buscarPorId(int id) {
        String sql = "SELECT * FROM cursos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Curso(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("estado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar curso: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Curso> listarTodos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cursos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Curso curso) {
        String sql = "UPDATE cursos SET nombre = ?, descripcion = ?, estado = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getEstado());
            stmt.setInt(4, curso.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar curso: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cursos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar curso: " + e.getMessage());
        }
    }
}