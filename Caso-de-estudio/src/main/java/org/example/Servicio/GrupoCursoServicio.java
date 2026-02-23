package org.example.Servicio;

import org.example.Modelo.GrupoCurso;
import org.example.Repositorio.IRepositorio;
import org.example.Repositorio.GrupoRepositorio;
import org.example.Repositorio.CursoRepositorio;
import java.util.List;

public class GrupoCursoServicio {
    private final IRepositorio<GrupoCurso> repositorioRelacion;
    private final GrupoRepositorio grupoRepo;
    private final CursoRepositorio cursoRepo;

    // Inyección de dependencias para asegurar que el servicio tenga acceso a todas las entidades necesarias
    public GrupoCursoServicio(IRepositorio<GrupoCurso> repositorioRelacion,
                              GrupoRepositorio grupoRepo,
                              CursoRepositorio cursoRepo) {
        this.repositorioRelacion = repositorioRelacion;
        this.grupoRepo = grupoRepo;
        this.cursoRepo = cursoRepo;
    }

    /*Vincula un curso a un grupo específico y  valida que ambos existan previamente.*/

    public void asignarCursoAGrupo(int id, int grupoId, int cursoId) throws Exception {
        // 1. Validar existencia del Grupo
        if (grupoRepo.buscarPorId(grupoId).isEmpty()) {
            throw new Exception("Error: El Grupo con ID " + grupoId + " no existe.");
        }

        // 2. Validar existencia del Curso
        if (cursoRepo.buscarPorId(cursoId).isEmpty()) {
            throw new Exception("Error: El Curso con ID " + cursoId + " no existe.");
        }

        // 3. Crear la relación si las validaciones pasan
        GrupoCurso nuevaAsignacion = new GrupoCurso(id, grupoId, cursoId);

        // 4. Persistir en el repositorio
        repositorioRelacion.guardar(nuevaAsignacion);
    }

    /*Lista todas las relaciones Grupo-Curso existentes.*/

    public List<GrupoCurso> obtenerTodasLasAsignaciones() {
        return repositorioRelacion.listarTodos();
    }

    /*Elimina una asignación específica por su ID de relación.*/
    public void eliminarAsignacion(int id) {
        repositorioRelacion.eliminar(id);
    }
}