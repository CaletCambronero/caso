package org.example.Servicio;

import org.example.Modelo.Estudiante;
import org.example.Repositorio.EstudianteRepositorio;
import java.util.List;

public class EstudianteServicio {
    private final EstudianteRepositorio repositorio;

    public EstudianteServicio(EstudianteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Estudiante estudiante) throws Exception {
        if (repositorio.buscarPorId(estudiante.getId()).isPresent()) {
            throw new Exception("Error: El ID " + estudiante.getId() + " ya está en uso.");
        }
        if (!estudiante.getEmail().contains("@")) {
            throw new Exception("Error: El formato de correo es inválido.");
        }
        repositorio.guardar(estudiante);
    }

    public List<Estudiante> listar() {
        return repositorio.listarTodos();
    }

    public void actualizar(Estudiante estudiante) throws Exception {
        if (repositorio.buscarPorId(estudiante.getId()).isEmpty()) {
            throw new Exception("Error: Estudiante no encontrado.");
        }
        repositorio.actualizar(estudiante);
    }

    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}