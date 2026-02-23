package org.example.Repositorio;


import org.example.Modelo.Grupo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrupoRepositorio implements IRepositorio<Grupo> {
    private List<Grupo> grupos = new ArrayList<>();

    @Override
    public void guardar(Grupo grupo) {
        grupos.add(grupo);
    }

    @Override
    public Optional<Grupo> buscarPorId(int id) {
        return grupos.stream()
                .filter(g -> g.getId() == id) // ID de ElementoAcademico
                .findFirst();
    }

    @Override
    public List<Grupo> listarTodos() {
        return new ArrayList<>(grupos);
    }

    @Override
    public void actualizar(Grupo grupoActualizado) {
        buscarPorId(grupoActualizado.getId()).ifPresent(g -> {
            g.setNombre(grupoActualizado.getNombre());
            g.setDescripcion(grupoActualizado.getDescripcion());
            g.setEstado(grupoActualizado.getEstado());
        });
    }

    @Override
    public void eliminar(int id) {
        grupos.removeIf(g -> g.getId() == id);
    }
}
