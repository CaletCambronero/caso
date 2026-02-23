package org.example.Repositorio;

import org.example.Modelo.Curso;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoRepositorio implements IRepositorio<Curso> {
    private List<Curso> cursos = new ArrayList<>();

    @Override
    public void guardar(Curso curso) {
        cursos.add(curso);
    }

    @Override
    public Optional<Curso> buscarPorId(int id) {
        return cursos.stream()
                .filter(c -> c.getId() == id) // Heredado de ElementoAcademico
                .findFirst();
    }

    @Override
    public List<Curso> listarTodos() {
        return new ArrayList<>(cursos);
    }

    @Override
    public void actualizar(Curso curso) {
        buscarPorId(curso.getId()).ifPresent(c -> {
            c.setNombre(curso.getNombre());
            c.setDescripcion(curso.getDescripcion());
            c.setEstado(curso.getEstado());
        });
    }

    @Override
    public void eliminar(int id) {
        cursos.removeIf(c -> c.getId() == id);
    }
}
