package org.example.Repositorio;

import org.example.Modelo.GrupoCurso;
import org.example.misc.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrupoCursoRepositorio implements IRepositorio<GrupoCurso> {

    @Override
    public void guardar(GrupoCurso grupoCurso) {
        String sql = "INSERT INTO grupo_cursos (id, grupo_id, curso_id) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, grupoCurso.getId());
            stmt.setInt(2, grupoCurso.getGrupoId());
            stmt.setInt(3, grupoCurso.getCursoId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar asignación: " + e.getMessage());
        }
    }

    @Override
    public Optional<GrupoCurso> buscarPorId(int id) {
        String sql = "SELECT * FROM grupo_cursos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new GrupoCurso(
                            rs.getInt("id"),
                            rs.getInt("grupo_id"),
                            rs.getInt("curso_id")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar asignación: " + e.getMessage());
        }
        return Optional.empty();
    }

    // Método especializado solicitado en tu código original
    public List<Integer> buscarCursosPorGrupo(int grupoId) {
        List<Integer> cursoIds = new ArrayList<>();
        String sql = "SELECT curso_id FROM grupo_cursos WHERE grupo_id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, grupoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cursoIds.add(rs.getInt("curso_id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cursos del grupo: " + e.getMessage());
        }
        return cursoIds;
    }

    @Override
    public List<GrupoCurso> listarTodos() {
        List<GrupoCurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupo_cursos";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new GrupoCurso(
                        rs.getInt("id"),
                        rs.getInt("grupo_id"),
                        rs.getInt("curso_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar asignaciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(GrupoCurso gc) {
        String sql = "UPDATE grupo_cursos SET grupo_id=?, curso_id=? WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gc.getGrupoId());
            stmt.setInt(2, gc.getCursoId());
            stmt.setInt(3, gc.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar asignación: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM grupo_cursos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar asignación: " + e.getMessage());
        }
    }
}