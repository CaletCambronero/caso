package org.example.Repositorio;

import org.example.Modelo.Grupo;
import org.example.misc.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrupoRepositorio implements IRepositorio<Grupo> {

    @Override
    public void guardar(Grupo grupo) {
        String sql = "INSERT INTO grupos (id, nombre, descripcion, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, grupo.getId());
            stmt.setString(2, grupo.getNombre());
            stmt.setString(3, grupo.getDescripcion());
            stmt.setString(4, grupo.getEstado());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar grupo: " + e.getMessage());
        }
    }

    @Override
    public Optional<Grupo> buscarPorId(int id) {
        String sql = "SELECT * FROM grupos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Grupo(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("estado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar grupo: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Grupo> listarTodos() {
        List<Grupo> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupos";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Grupo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar grupos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Grupo grupo) {
        String sql = "UPDATE grupos SET nombre=?, descripcion=?, estado=? WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, grupo.getNombre());
            stmt.setString(2, grupo.getDescripcion());
            stmt.setString(3, grupo.getEstado());
            stmt.setInt(4, grupo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar grupo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM grupos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar grupo: " + e.getMessage());
        }
    }
}