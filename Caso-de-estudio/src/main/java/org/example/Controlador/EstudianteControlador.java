package org.example.Controlador;

import org.example.Modelo.Estudiante;
import org.example.Servicio.EstudianteServicio;
import java.time.LocalDate;
import java.util.Scanner;

public class EstudianteControlador {
    private final EstudianteServicio servicio;
    private final Scanner scanner;

    public EstudianteControlador(EstudianteServicio servicio) {
        this.servicio = servicio;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MÓDULO DE ESTUDIANTES ---");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Listar Estudiantes");
            System.out.println("3. Eliminar Estudiante");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> eliminar();            }
        } while (opcion != 0);
    }

    private void registrar() {
        try {
            System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Nombre: "); String nombre = scanner.nextLine();
            System.out.print("Identificación: "); String dni = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Fecha Nacimiento (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            Estudiante nuevo = new Estudiante(id, nombre, dni, email, fecha, "ACTIVO");
            servicio.registrar(nuevo);
            System.out.println("¡Estudiante registrado con éxito!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void listar() {
        servicio.listar().forEach(e -> {
            System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre() + " | Email: " + e.getEmail());
        });
    }

    private void eliminar() {
        System.out.print("ID del estudiante a eliminar: ");
        int id = scanner.nextInt();
        servicio.eliminar(id);
        System.out.println("Operación completada.");
    }
}