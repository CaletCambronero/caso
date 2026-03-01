package org.example.Vista;

import org.example.Modelo.Grupo;
import java.util.List;
import java.util.Scanner;

public class GrupoVista {
    private final Scanner scanner;

    public GrupoVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n===== GESTIÓN DE GRUPOS =====");
        System.out.println("1. Registrar nuevo grupo");
        System.out.println("2. Listar grupos");
        System.out.println("3. Buscar grupo");
        System.out.println("4. Modificar grupo");
        System.out.println("5. Eliminar grupo");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Grupo pedirDatosRegistro() {
        try {
            System.out.println("\n--- Registro de Grupo ---");
            System.out.print("ID del grupo: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre (ej. G-01): ");
            String nombre = scanner.nextLine();
            System.out.print("Descripción: ");
            String desc = scanner.nextLine();
            return new Grupo(id, nombre, desc, "ACTIVO");
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: ID debe ser numérico.");
            return null;
        }
    }

    public void listar(List<Grupo> lista) {
        System.out.println("\n--- Lista de Grupos ---");
        if (lista.isEmpty()) System.out.println("No hay grupos.");
        else lista.forEach(g -> System.out.println(g.getId() + " - " + g.getNombre() + " (" + g.getEstado() + ")"));
    }

    public int pedirId() {
        System.out.print("Ingrese ID del grupo: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarDetalle(Grupo g) {
        System.out.println("Grupo: " + g.getNombre() + " | " + g.getDescripcion());
    }

    public Grupo pedirDatosEdicion(Grupo actual) {
        System.out.println("Editando: " + actual.getNombre());
        System.out.print("Nuevo nombre (Enter mantiene): ");
        String n = scanner.nextLine();
        if(!n.isEmpty()) actual.setNombre(n);

        System.out.print("Nueva descripción (Enter mantiene): ");
        String d = scanner.nextLine();
        if(!d.isEmpty()) actual.setDescripcion(d);

        System.out.print("Nuevo estado: ");
        String e = scanner.nextLine();
        if(!e.isEmpty()) actual.setEstado(e);

        return actual;
    }

    public boolean confirmar() {
        System.out.print("¿Seguro? (S/N): ");
        return scanner.nextLine().equalsIgnoreCase("S");
    }

    public void mostrarMensaje(String m) { System.out.println(m); }
}