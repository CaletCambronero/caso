package org.example.Repositorio;

import org.example.Modelo.Estudiante;
import org.example.misc.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteRepositorio implements IRepositorio<Estudiante> {

    @Override
    public void guardar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (id, nombre, identificacion, email, fecha_nacimiento, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estudiante.getId());
            stmt.setString(2, estudiante.getNombre());
            stmt.setString(3, estudiante.getIdentificacion());
            stmt.setString(4, estudiante.getEmail());
            // Conversión necesaria para fechas SQL
            stmt.setDate(5, Date.valueOf(estudiante.getFechaNacimiento()));
            stmt.setString(6, estudiante.getEstado());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar estudiante: " + e.getMessage());
        }
    }

    @Override
    public Optional<Estudiante> buscarPorId(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Estudiante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("identificacion"),
                            rs.getString("email"),
                            rs.getDate("fecha_nacimiento").toLocalDate(), // Conversión inversa
                            rs.getString("estado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiante: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Estudiante est) {
        String sql = "UPDATE estudiantes SET nombre=?, identificacion=?, email=?, fecha_nacimiento=?, estado=? WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, est.getNombre());
            stmt.setString(2, est.getIdentificacion());
            stmt.setString(3, est.getEmail());
            stmt.setDate(4, Date.valueOf(est.getFechaNacimiento()));
            stmt.setString(5, est.getEstado());
            stmt.setInt(6, est.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar estudiante: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM estudiantes WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar estudiante: " + e.getMessage());
        }
    }
}