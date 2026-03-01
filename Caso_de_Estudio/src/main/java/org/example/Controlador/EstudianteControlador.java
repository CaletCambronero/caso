package org.example.Controlador;

import org.example.Modelo.Estudiante;
import org.example.Servicio.EstudianteServicio;
import org.example.Vista.EstudianteVista;
import java.util.Optional;

public class EstudianteControlador {
    private final EstudianteServicio servicio;
    private final EstudianteVista vista;

    public EstudianteControlador(EstudianteServicio servicio, EstudianteVista vista) {
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
                    case 3 -> modificar();
                    case 4 -> eliminar();
                    case 0 -> vista.mostrarMensaje("Regresando...");
                    default -> vista.mostrarMensaje("Opción no válida.");
                }
            } catch (Exception e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void registrar() throws Exception {
        Estudiante nuevo = vista.pedirDatosRegistro();
        if (nuevo != null) {
            servicio.registrar(nuevo);
            vista.mostrarMensaje("¡Estudiante registrado con éxito!");
        }
    }

    private void listar() {
        vista.mostrarLista(servicio.listar());
    }

    private void modificar() throws Exception {
        int id = vista.pedirId("modificar");
        Optional<Estudiante> opt = servicio.buscarPorId(id);

        if (opt.isPresent()) {
            Estudiante actualizado = vista.pedirDatosActualizacion(opt.get());
            servicio.actualizar(actualizado);
            vista.mostrarMensaje("Estudiante actualizado.");
        } else {
            vista.mostrarMensaje("Estudiante no encontrado.");
        }
    }

    private void eliminar() {
        int id = vista.pedirId("eliminar");
        servicio.eliminar(id);
        vista.mostrarMensaje("Operación completada.");
    }
}