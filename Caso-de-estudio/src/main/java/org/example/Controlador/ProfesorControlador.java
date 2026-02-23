package org.example.Controlador;

import org.example.Modelo.Profesor;
import org.example.Servicio.ProfesorServicio;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class ProfesorControlador {
    private final ProfesorServicio servicio;
    private final Scanner scanner;

    public ProfesorControlador(ProfesorServicio servicio) {
        this.servicio = servicio;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== MÓDULO DE PROFESORES =====");
            System.out.println("1. Registrar nuevo profesor");
            System.out.println("2. Listar todos los profesores");
            System.out.println("3. Buscar profesor por ID");
            System.out.println("4. Modificar profesor existente");
            System.out.println("5. Eliminar profesor");
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
                System.out.println("Error: Por favor ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void registrar() throws Exception {
        System.out.println("\n--- Registro de Profesor ---");
        System.out.print("Ingrese ID numérico: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación (DNI/Cédula): ");
        String identificacion = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Departamento académico: ");
        String departamento = scanner.nextLine();

        // El estado se inicializa como ACTIVO por defecto
        Profesor nuevo = new Profesor(id, nombre, identificacion, email, departamento, "ACTIVO");
        servicio.registrar(nuevo);
        System.out.println("¡Profesor registrado exitosamente!");
    }

    private void listar() {
        System.out.println("\n--- Lista de Profesores ---");
        List<Profesor> lista = servicio.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            lista.forEach(p -> System.out.println(
                    "ID: " + p.getId() +
                            " | Nombre: " + p.getNombre() +
                            " | Depto: " + p.getDepartamento() +
                            " | Estado: " + p.getEstado()
            ));
        }
    }

    private void buscar() {
        System.out.print("\nIngrese el ID del profesor a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        servicio.buscarPorId(id).ifPresentOrElse(
                p -> System.out.println("Encontrado: " + p.getNombre() + " del departamento " + p.getDepartamento()),
                () -> System.out.println("Profesor con ID " + id + " no encontrado.")
        );
    }

    private void modificar() throws Exception {
        System.out.print("\nIngrese el ID del profesor a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Profesor> actualOpt = servicio.buscarPorId(id);
        if (actualOpt.isEmpty()) {
            System.out.println("No se encontró un profesor con ese ID.");
            return;
        }

        Profesor actual = actualOpt.get();
        System.out.println("Modificando a: " + actual.getNombre());

        System.out.print("Nuevo nombre (dejar vacío para mantener '" + actual.getNombre() + "'): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) actual.setNombre(nombre);

        System.out.print("Nuevo departamento (dejar vacío para mantener '" + actual.getDepartamento() + "'): ");
        String depto = scanner.nextLine();
        if (!depto.isEmpty()) actual.setDepartamento(depto);

        System.out.print("Nuevo estado (ACTIVO/INACTIVO): ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) actual.setEstado(estado);

        servicio.actualizar(actual);
        System.out.println("Datos actualizados correctamente.");
    }

    private void eliminar() {
        System.out.print("\nIngrese el ID del profesor a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("¿Está seguro de eliminar al profesor? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            servicio.eliminar(id);
            System.out.println("Profesor eliminado (si existía).");
        }
    }
}