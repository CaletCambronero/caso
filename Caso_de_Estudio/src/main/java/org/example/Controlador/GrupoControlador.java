package org.example.Controlador;

import org.example.Modelo.Grupo;
import org.example.Servicio.GrupoServicio;
import org.example.Vista.GrupoVista;
import java.util.Optional;

public class GrupoControlador {
    private final GrupoServicio servicio;
    private final GrupoVista vista;

    public GrupoControlador(GrupoServicio servicio, GrupoVista vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void ejecutar() {
        int opcion = -1;
        while (opcion != 0) {
            opcion = vista.mostrarMenu();
            try {
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> modificar();
                    case 5 -> eliminar();
                    case 0 -> vista.mostrarMensaje("Regresando...");
                    default -> vista.mostrarMensaje("Opción inválida.");
                }
            } catch (Exception e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void registrar() throws Exception {
        Grupo g = vista.pedirDatosRegistro();
        if(g != null) {
            servicio.crear(g);
            vista.mostrarMensaje("Grupo creado.");
        }
    }

    private void listar() { vista.listar(servicio.listar()); }

    private void buscar() {
        int id = vista.pedirId();
        servicio.buscarPorId(id).ifPresentOrElse(
                vista::mostrarDetalle,
                () -> vista.mostrarMensaje("No encontrado.")
        );
    }

    private void modificar() throws Exception {
        int id = vista.pedirId();
        Optional<Grupo> opt = servicio.buscarPorId(id);
        if(opt.isPresent()) {
            Grupo mod = vista.pedirDatosEdicion(opt.get());
            servicio.actualizar(mod);
            vista.mostrarMensaje("Actualizado.");
        } else {
            vista.mostrarMensaje("No existe.");
        }
    }

    private void eliminar() {
        int id = vista.pedirId();
        if(vista.confirmar()) {
            servicio.eliminar(id);
            vista.mostrarMensaje("Eliminado.");
        }
    }
}