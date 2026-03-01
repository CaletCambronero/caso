package org.example.Vista;

import org.example.Modelo.Estudiante;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EstudianteVista {
    private final Scanner scanner;

    public EstudianteVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuYSeleccionar() {
        System.out.println("\n--- MÓDULO DE ESTUDIANTES ---");
        System.out.println("1. Registrar Estudiante");
        System.out.println("2. Listar Estudiantes");
        System.out.println("3. Modificar Estudiante");
        System.out.println("4. Eliminar Estudiante");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Estudiante pedirDatosRegistro() {
        try {
            System.out.println("\n--- Registro de Estudiante ---");
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Identificación: ");
            String dni = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Fecha Nacimiento (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            return new Estudiante(id, nombre, dni, email, fecha, "ACTIVO");
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: El ID debe ser numérico.");
            return null;
        } catch (DateTimeParseException e) {
            mostrarMensaje("Error: Formato de fecha inválido. Use YYYY-MM-DD.");
            return null;
        }
    }

    public void mostrarLista(List<Estudiante> lista) {
        System.out.println("\n--- Lista de Estudiantes ---");
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            lista.forEach(e -> System.out.println(
                    "ID: " + e.getId() +
                            " | Nombre: " + e.getNombre() +
                            " | Email: " + e.getEmail() +
                            " | Estado: " + e.getEstado()
            ));
        }
    }

    public int pedirId(String accion) {
        System.out.print("\nID del estudiante a " + accion + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Estudiante pedirDatosActualizacion(Estudiante actual) {
        System.out.println("Editando a: " + actual.getNombre());

        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) actual.setNombre(nombre);

        System.out.print("Nuevo Email (Enter para mantener): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) actual.setEmail(email);

        System.out.print("Nueva Fecha Nacimiento YYYY-MM-DD (Enter para mantener): ");
        String fechaStr = scanner.nextLine();
        if (!fechaStr.isEmpty()) {
            try {
                actual.setFechaNacimiento(LocalDate.parse(fechaStr));
            } catch (DateTimeParseException e) {
                mostrarMensaje("Fecha inválida, se mantendrá la anterior.");
            }
        }

        System.out.print("Nuevo estado (ACTIVO/INACTIVO): ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) actual.setEstado(estado);

        return actual;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}