package org.example.Controlador;

import org.example.Modelo.Curso;
import org.example.Servicio.CursoServicio;
import org.example.Vista.CursoVista;
import java.util.Optional;

public class CursoControlador {
    private final CursoServicio servicio;
    private final CursoVista vista;

    public CursoControlador(CursoServicio servicio, CursoVista vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void ejecutar() {
        int opcion = -1;
        while (opcion != 0) {
            opcion = vista.mostrarMenuYSeleccionar();
            try {
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> modificar();
                    case 5 -> eliminar();
                    case 0 -> vista.mostrarMensaje("Regresando...");
                    default -> vista.mostrarMensaje("Opción no válida.");
                }
            } catch (Exception e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void registrar() throws Exception {
        Curso nuevo = vista.pedirDatosCreacion();
        if (nuevo != null) {
            servicio.crear(nuevo);
            vista.mostrarMensaje("¡Curso creado exitosamente!");
        }
    }

    private void listar() {
        vista.mostrarLista(servicio.listar());
    }

    private void buscar() {
        int id = vista.pedirId("consultar");
        servicio.buscarPorId(id).ifPresentOrElse(
                vista::mostrarDetalle,
                () -> vista.mostrarMensaje("Curso no encontrado.")
        );
    }

    private void modificar() throws Exception {
        int id = vista.pedirId("modificar");
        Optional<Curso> opt = servicio.buscarPorId(id);

        if (opt.isPresent()) {
            Curso modificado = vista.pedirDatosEdicion(opt.get());
            servicio.actualizar(modificado);
            vista.mostrarMensaje("Curso actualizado correctamente.");
        } else {
            vista.mostrarMensaje("El curso no existe.");
        }
    }

    private void eliminar() {
        int id = vista.pedirId("eliminar");
        if (vista.confirmarEliminacion()) {
            servicio.eliminar(id);
            vista.mostrarMensaje("Eliminación finalizada.");
        }
    }
}