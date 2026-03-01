package org.example.Controlador;

import org.example.Modelo.Profesor;
import org.example.Servicio.ProfesorServicio;
import org.example.Vista.ProfesorVista;

import java.util.List;
import java.util.Optional;

public class ProfesorControlador {
    private final ProfesorServicio servicio;
    private final ProfesorVista vista;

    // Inyectamos tanto el Servicio como la Vista
    public ProfesorControlador(ProfesorServicio servicio, ProfesorVista vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void ejecutar() {
        int opcion = -1;
        while (opcion != 0) {
            // El controlador le pide a la vista que muestre el menú y le devuelva la elección
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
        // 1. Pedir datos a la vista
        Profesor nuevo = vista.pedirDatosRegistro();

        // 2. Si la vista devolvió null (error de formato), abortamos
        if (nuevo == null) return;

        // 3. Llamar al servicio
        servicio.registrar(nuevo);

        // 4. Confirmar a la vista
        vista.mostrarMensaje("¡Profesor registrado exitosamente!");
    }

    private void listar() {
        List<Profesor> lista = servicio.listar();
        vista.mostrarLista(lista);
    }

    private void buscar() {
        int id = vista.pedirId("buscar");
        Optional<Profesor> prof = servicio.buscarPorId(id);

        if (prof.isPresent()) {
            vista.mostrarProfesor(prof.get());
        } else {
            vista.mostrarMensaje("Profesor no encontrado.");
        }
    }

    private void modificar() throws Exception {
        int id = vista.pedirId("modificar");
        Optional<Profesor> actualOpt = servicio.buscarPorId(id);

        if (actualOpt.isEmpty()) {
            vista.mostrarMensaje("No se encontró un profesor con ese ID.");
            return;
        }

        // Le pasamos el profesor actual a la vista para que el usuario lo edite
        Profesor profesorActualizado = vista.pedirDatosActualizacion(actualOpt.get());

        servicio.actualizar(profesorActualizado);
        vista.mostrarMensaje("Datos actualizados correctamente.");
    }

    private void eliminar() {
        int id = vista.pedirId("eliminar");
        if (vista.confirmarEliminacion()) {
            servicio.eliminar(id);
            vista.mostrarMensaje("Profesor eliminado (si existía).");
        }
    }
}