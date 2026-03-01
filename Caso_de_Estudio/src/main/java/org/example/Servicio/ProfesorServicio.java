package org.example.Servicio;

import org.example.Modelo.Profesor;
import org.example.Repositorio.ProfesorRepositorio;
import java.util.List;
import java.util.Optional;

public class ProfesorServicio {
    private final ProfesorRepositorio repositorio;

    public ProfesorServicio(ProfesorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Profesor profesor) throws Exception {
        // Validación de ID duplicado
        if (repositorio.buscarPorId(profesor.getId()).isPresent()) {
            throw new Exception("Error: Ya existe un profesor con el ID " + profesor.getId());
        }

        // Validación de campo específico de Profesor
        if (profesor.getDepartamento() == null || profesor.getDepartamento().isEmpty()) {
            throw new Exception("Error: El departamento es obligatorio.");
        }

        repositorio.guardar(profesor);
    }

    public List<Profesor> listar() {
        return repositorio.listarTodos();
    }

    public Optional<Profesor> buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    public void actualizar(Profesor profesor) throws Exception {
        // Validar que el profesor existe antes de intentar actualizarlo
        if (repositorio.buscarPorId(profesor.getId()).isEmpty()) {
            throw new Exception("Error: No se encontró el profesor con ID " + profesor.getId() + " para actualizar.");
        }

        // Validar nuevamente que el departamento no quede vacío tras la actualización
        if (profesor.getDepartamento() == null || profesor.getDepartamento().isEmpty()) {
            throw new Exception("Error: El departamento no puede estar vacío.");
        }

        repositorio.actualizar(profesor);
    }

    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}