package org.example.Servicio;

import org.example.Modelo.Curso;
import org.example.Repositorio.CursoRepositorio;
import java.util.List;
import java.util.Optional;

public class CursoServicio {
    private final CursoRepositorio repositorio;

    public CursoServicio(CursoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void crear(Curso curso) throws Exception {
        // Validar que el ID no exista previamente
        if (repositorio.buscarPorId(curso.getId()).isPresent()) {
            throw new Exception("Error: Ya existe un curso registrado con el ID " + curso.getId());
        }

        // Validar que el nombre sea obligatorio
        if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
            throw new Exception("Error: El nombre del curso es obligatorio.");
        }

        repositorio.guardar(curso);
    }


    public List<Curso> listar() {
        return repositorio.listarTodos();
    }


    public Optional<Curso> buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    public void actualizar(Curso curso) throws Exception {
        // Verificar existencia antes de actualizar
        if (repositorio.buscarPorId(curso.getId()).isEmpty()) {
            throw new Exception("Error: No se puede actualizar un curso que no existe (ID: " + curso.getId() + ").");
        }

        // Re-validar nombre en la actualización
        if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
            throw new Exception("Error: El nombre del curso no puede quedar vacío.");
        }

        repositorio.actualizar(curso);
    }

    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}