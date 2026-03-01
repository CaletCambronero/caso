package org.example.Vista;

import org.example.Modelo.Profesor;
import java.util.List;
import java.util.Scanner;

public class ProfesorVista {
    private final Scanner scanner;

    public ProfesorVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuYSeleccionar() {
        System.out.println("\n===== MÓDULO DE PROFESORES =====");
        System.out.println("1. Registrar nuevo profesor");
        System.out.println("2. Listar todos los profesores");
        System.out.println("3. Buscar profesor por ID");
        System.out.println("4. Modificar profesor existente");
        System.out.println("5. Eliminar profesor");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Profesor pedirDatosRegistro() {
        System.out.println("\n--- Registro de Profesor ---");
        try {
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

            // Retornamos un objeto temporal con los datos recolectados
            return new Profesor(id, nombre, identificacion, email, departamento, "ACTIVO");
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser numérico.");
            return null;
        }
    }

    public void mostrarLista(List<Profesor> lista) {
        System.out.println("\n--- Lista de Profesores ---");
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

    public int pedirId(String accion) {
        System.out.print("\nIngrese el ID del profesor a " + accion + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarProfesor(Profesor p) {
        System.out.println("---------------------------------");
        System.out.println("Datos del Profesor:");
        System.out.println("Nombre: " + p.getNombre());
        System.out.println("Departamento: " + p.getDepartamento());
        System.out.println("Email: " + p.getEmail());
        System.out.println("Estado: " + p.getEstado());
        System.out.println("---------------------------------");
    }

    // Retorna el objeto modificado (o null si no se modificó nada relevante)
    public Profesor pedirDatosActualizacion(Profesor actual) {
        System.out.println("Modificando a: " + actual.getNombre());

        System.out.print("Nuevo nombre (Enter para mantener '" + actual.getNombre() + "'): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) actual.setNombre(nombre);

        System.out.print("Nuevo departamento (Enter para mantener '" + actual.getDepartamento() + "'): ");
        String depto = scanner.nextLine();
        if (!depto.isEmpty()) actual.setDepartamento(depto);

        System.out.print("Nuevo estado (ACTIVO/INACTIVO): ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) actual.setEstado(estado);

        return actual;
    }

    public boolean confirmarEliminacion() {
        System.out.print("¿Está seguro de eliminar al profesor? (S/N): ");
        return scanner.nextLine().equalsIgnoreCase("S");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}