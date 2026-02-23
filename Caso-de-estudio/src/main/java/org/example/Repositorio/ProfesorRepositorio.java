package org.example.Repositorio;

import org.example.Modelo.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesorRepositorio implements IRepositorio<Profesor> {
    private List<Profesor> profesores = new ArrayList<>();

    @Override
    public void guardar(Profesor profesor) {
        profesores.add(profesor);
    }

    @Override
    public Optional<Profesor> buscarPorId(int id) {
        return profesores.stream()
                .filter(p -> p.getId() == id) // ID heredado de Persona
                .findFirst();
    }

    @Override
    public List<Profesor> listarTodos() {
        return new ArrayList<>(profesores);
    }

    @Override
    public void actualizar(Profesor profesorActualizado) {
        buscarPorId(profesorActualizado.getId()).ifPresent(p -> {
            p.setNombre(profesorActualizado.getNombre());
            p.setIdentificacion(profesorActualizado.getIdentificacion());
            p.setEmail(profesorActualizado.getEmail());
            p.setDepartamento(profesorActualizado.getDepartamento()); // Atributo propio
            p.setEstado(profesorActualizado.getEstado());
        });
    }

    @Override
    public void eliminar(int id) {
        profesores.removeIf(p -> p.getId() == id);
    }
}
