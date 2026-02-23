package org.example.Servicio;

import org.example.Modelo.Grupo;
import org.example.Repositorio.GrupoRepositorio;
import java.util.List;
import java.util.Optional;

public class GrupoServicio {
    private final GrupoRepositorio repositorio;

    public GrupoServicio(GrupoRepositorio repositorio) {
        this.repositorio = repositorio;
    }


    public void crear(Grupo grupo) throws Exception {
        // Validación de ID único usando el ID de ElementoAcademico
        if (repositorio.buscarPorId(grupo.getId()).isPresent()) {
            throw new Exception("Error: El ID " + grupo.getId() + " ya está asignado a otro grupo.");
        }

        // Validación de nombre obligatorio
        if (grupo.getNombre() == null || grupo.getNombre().trim().isEmpty()) {
            throw new Exception("Error: El nombre del grupo es obligatorio y no puede estar vacío.");
        }

        // Asignar "ACTIVO" si el estado es nulo o vacío para mantener consistencia
        if (grupo.getEstado() == null || grupo.getEstado().trim().isEmpty()) {
            grupo.setEstado("ACTIVO");
        }

        repositorio.guardar(grupo);
    }


    public List<Grupo> listar() {
        return repositorio.listarTodos();
    }


    public Optional<Grupo> buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }


    public void actualizar(Grupo grupo) throws Exception {
        // Verificar existencia antes de proceder
        if (repositorio.buscarPorId(grupo.getId()).isEmpty()) {
            throw new Exception("Error: No se encontró el grupo con ID " + grupo.getId() + " para actualizar.");
        }

        // Impedir que el nombre se actualice a un valor vacío
        if (grupo.getNombre() == null || grupo.getNombre().trim().isEmpty()) {
            throw new Exception("Error: El nombre del grupo no puede quedar vacío durante la actualización.");
        }

        repositorio.actualizar(grupo);
    }

    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}