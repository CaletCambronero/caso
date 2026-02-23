package org.example.Repositorio;


import org.example.Modelo.GrupoCurso;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GrupoCursoRepositorio implements IRepositorio<GrupoCurso> {
    private List<GrupoCurso> asignaciones = new ArrayList<>();

    @Override
    public void guardar(GrupoCurso grupoCurso) {
        asignaciones.add(grupoCurso);
    }

    @Override
    public Optional<GrupoCurso> buscarPorId(int id) {
        return asignaciones.stream()
                .filter(gc -> gc.getId() == id)
                .findFirst();
    }

    // Método especializado para saber qué cursos tiene un grupo
    public List<Integer> buscarCursosPorGrupo(int grupoId) {
        return asignaciones.stream()
                .filter(gc -> gc.getGrupoId() == grupoId)
                .map(GrupoCurso::getCursoId)
                .collect(Collectors.toList());
    }

    @Override
    public List<GrupoCurso> listarTodos() {
        return new ArrayList<>(asignaciones);
    }

    @Override
    public void actualizar(GrupoCurso gcActualizado) {
        buscarPorId(gcActualizado.getId()).ifPresent(gc -> {
            gc.setGrupoId(gcActualizado.getGrupoId());
            gc.setCursoId(gcActualizado.getCursoId());
        });
    }

    @Override
    public void eliminar(int id) {
        asignaciones.removeIf(gc -> gc.getId() == id);
    }
}