package org.example.Controlador;

import org.example.Modelo.Curso;
import org.example.Servicio.CursoServicio;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class CursoControlador {
    private final CursoServicio servicio;
    private final Scanner scanner;

    public CursoControlador(CursoServicio servicio) {
        this.servicio = servicio;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Menú principal del módulo de cursos.
     */
    public void ejecutar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== MÓDULO DE CURSOS =====");
            System.out.println("1. Crear nuevo curso");
            System.out.println("2. Listar todos los cursos");
            System.out.println("3. Buscar curso por ID");
            System.out.println("4. Modificar curso existente");
            System.out.println("5. Eliminar curso");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> modificar();
                    case 5 -> eliminar();
                    case 0 -> System.out.println("Regresando...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido para la opción.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void registrar() throws Exception {
        System.out.println("\n--- Registro de Curso ---");
        System.out.print("Ingrese ID del curso: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre del curso: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción del contenido: ");
        String descripcion = scanner.nextLine();

        // Se crea el curso con estado ACTIVO por defecto
        Curso nuevo = new Curso(id, nombre, descripcion, "ACTIVO");
        servicio.crear(nuevo);
        System.out.println("¡Curso creado exitosamente!");
    }

    private void listar() {
        System.out.println("\n--- Lista de Cursos ---");
        List<Curso> lista = servicio.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay cursos registrados en el sistema.");
        } else {
            lista.forEach(c -> System.out.println(
                    "ID: " + c.getId() +
                            " | Nombre: " + c.getNombre() +
                            " | Estado: " + c.getEstado()
            ));
        }
    }

    private void buscar() {
        System.out.print("\nIngrese el ID del curso a consultar: ");
        int id = Integer.parseInt(scanner.nextLine());
        servicio.buscarPorId(id).ifPresentOrElse(
                c -> {
                    System.out.println("Detalles del Curso:");
                    System.out.println("Nombre: " + c.getNombre());
                    System.out.println("Descripción: " + c.getDescripcion());
                    System.out.println("Estado: " + c.getEstado());
                },
                () -> System.out.println("Curso con ID " + id + " no encontrado.")
        );
    }

    private void modificar() throws Exception {
        System.out.print("\nIngrese el ID del curso a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Curso> actualOpt = servicio.buscarPorId(id);
        if (actualOpt.isEmpty()) {
            System.out.println("El curso solicitado no existe.");
            return;
        }

        Curso actual = actualOpt.get();
        System.out.println("Editando curso: " + actual.getNombre());

        System.out.print("Nuevo nombre (Enter para mantener '" + actual.getNombre() + "'): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) actual.setNombre(nombre);

        System.out.print("Nueva descripción (Enter para mantener '" + actual.getDescripcion() + "'): ");
        String desc = scanner.nextLine();
        if (!desc.isEmpty()) actual.setDescripcion(desc);

        System.out.print("Nuevo estado (ACTIVO/INACTIVO): ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) actual.setEstado(estado);

        servicio.actualizar(actual);
        System.out.println("Curso actualizado correctamente.");
    }

    private void eliminar() {
        System.out.print("\nIngrese el ID del curso a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("¿Confirmar eliminación? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            servicio.eliminar(id);
            System.out.println("Proceso de eliminación finalizado.");
        }
    }
}