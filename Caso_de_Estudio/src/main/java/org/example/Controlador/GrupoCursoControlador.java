package org.example.Controlador;

import org.example.Servicio.GrupoCursoServicio;
import org.example.Vista.GrupoCursoVista;

public class GrupoCursoControlador {
    private final GrupoCursoServicio servicio;
    private final GrupoCursoVista vista;

    public GrupoCursoControlador(GrupoCursoServicio servicio, GrupoCursoVista vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void ejecutar() {
        int opcion = -1;
        while (opcion != 0) {
            opcion = vista.mostrarMenu();
            try {
                switch (opcion) {
                    case 1 -> asignar();
                    case 2 -> listar();
                    case 3 -> eliminar();
                    case 0 -> vista.mostrarMensaje("Regresando...");
                    default -> vista.mostrarMensaje("Opción no válida.");
                }
            } catch (Exception e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void asignar() throws Exception {
        int[] datos = vista.pedirDatosAsignacion();
        if (datos != null) {
            // datos[0]=id, datos[1]=grupoId, datos[2]=cursoId
            servicio.asignarCursoAGrupo(datos[0], datos[1], datos[2]);
            vista.mostrarMensaje("Asignación creada.");
        } else {
            vista.mostrarMensaje("Error en los datos numéricos.");
        }
    }

    private void listar() {
        vista.listar(servicio.obtenerTodasLasAsignaciones());
    }

    private void eliminar() {
        int id = vista.pedirId();
        servicio.eliminarAsignacion(id);
        vista.mostrarMensaje("Eliminado.");
    }
}