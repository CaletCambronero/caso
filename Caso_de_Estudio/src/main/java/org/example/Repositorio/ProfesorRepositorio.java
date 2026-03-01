package org.example.Repositorio;

import org.example.Modelo.Profesor;
import org.example.misc.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesorRepositorio implements IRepositorio<Profesor> {

    @Override
    public void guardar(Profesor profesor) {
        String sql = "INSERT INTO profesores (id, nombre, identificacion, email, departamento, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, profesor.getId());
            stmt.setString(2, profesor.getNombre());
            stmt.setString(3, profesor.getIdentificacion());
            stmt.setString(4, profesor.getEmail());
            stmt.setString(5, profesor.getDepartamento());
            stmt.setString(6, profesor.getEstado());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar profesor: " + e.getMessage());
        }
    }

    @Override
    public Optional<Profesor> buscarPorId(int id) {
        String sql = "SELECT * FROM profesores WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Profesor(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("identificacion"),
                            rs.getString("email"),
                            rs.getString("departamento"),
                            rs.getString("estado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar profesor: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Profesor> listarTodos() {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Profesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("email"),
                        rs.getString("departamento"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar profesores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Profesor prof) {
        String sql = "UPDATE profesores SET nombre=?, identificacion=?, email=?, departamento=?, estado=? WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prof.getNombre());
            stmt.setString(2, prof.getIdentificacion());
            stmt.setString(3, prof.getEmail());
            stmt.setString(4, prof.getDepartamento());
            stmt.setString(5, prof.getEstado());
            stmt.setInt(6, prof.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar profesor: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM profesores WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar profesor: " + e.getMessage());
        }
    }
}