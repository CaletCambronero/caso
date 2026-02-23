package org.example.Repositorio;

import org.example.Modelo.Estudiante;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteRepositorio implements IRepositorio<Estudiante> {
    private List<Estudiante> estudiantes = new ArrayList<>();

    @Override
    public void guardar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    @Override
    public Optional<Estudiante> buscarPorId(int id) {
        return estudiantes.stream()
                .filter(e -> e.getId() == id) // Usa getId() heredado de Persona
                .findFirst();
    }

    @Override
    public List<Estudiante> listarTodos() {
        return new ArrayList<>(estudiantes);
    }

    @Override
    public void actualizar(Estudiante estudianteActualizado) {
        buscarPorId(estudianteActualizado.getId()).ifPresent(e -> {
            e.setNombre(estudianteActualizado.getNombre());
            e.setEmail(estudianteActualizado.getEmail());
            e.setFechaNacimiento(estudianteActualizado.getFechaNacimiento()); // Atributo propio
            e.setEstado(estudianteActualizado.getEstado());
        });
    }

    @Override
    public void eliminar(int id) {
        // Implementación de borrado físico, aunque podrías hacer borrado lógico usando setEstado
        estudiantes.removeIf(e -> e.getId() == id);
    }
}
