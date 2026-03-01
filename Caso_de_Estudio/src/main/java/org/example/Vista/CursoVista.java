package org.example.Vista;

import org.example.Modelo.Curso;
import java.util.List;
import java.util.Scanner;

public class CursoVista {
    private final Scanner scanner;

    public CursoVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuYSeleccionar() {
        System.out.println("\n===== MÓDULO DE CURSOS =====");
        System.out.println("1. Crear nuevo curso");
        System.out.println("2. Listar todos los cursos");
        System.out.println("3. Buscar curso por ID");
        System.out.println("4. Modificar curso");
        System.out.println("5. Eliminar curso");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Curso pedirDatosCreacion() {
        try {
            System.out.println("\n--- Registro de Curso ---");
            System.out.print("ID del curso: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre del curso: ");
            String nombre = scanner.nextLine();
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            return new Curso(id, nombre, descripcion, "ACTIVO");
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: ID inválido.");
            return null;
        }
    }

    public void mostrarLista(List<Curso> lista) {
        System.out.println("\n--- Lista de Cursos ---");
        if (lista.isEmpty()) {
            System.out.println("No hay cursos registrados.");
        } else {
            lista.forEach(c -> System.out.println(
                    "ID: " + c.getId() + " | Nombre: " + c.getNombre() + " | Estado: " + c.getEstado()
            ));
        }
    }

    public int pedirId(String accion) {
        System.out.print("\nIngrese ID del curso a " + accion + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarDetalle(Curso c) {
        System.out.println("--- Detalle del Curso ---");
        System.out.println("Nombre: " + c.getNombre());
        System.out.println("Descripción: " + c.getDescripcion());
        System.out.println("Estado: " + c.getEstado());
    }

    public Curso pedirDatosEdicion(Curso actual) {
        System.out.println("Editando curso: " + actual.getNombre());

        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) actual.setNombre(nombre);

        System.out.print("Nueva descripción (Enter para mantener): ");
        String desc = scanner.nextLine();
        if (!desc.isEmpty()) actual.setDescripcion(desc);

        System.out.print("Nuevo estado (ACTIVO/INACTIVO): ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) actual.setEstado(estado);

        return actual;
    }

    public boolean confirmarEliminacion() {
        System.out.print("¿Confirmar eliminación? (S/N): ");
        return scanner.nextLine().equalsIgnoreCase("S");
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}