package org.example.Vista;

import org.example.Modelo.GrupoCurso;
import java.util.List;
import java.util.Scanner;

public class GrupoCursoVista {
    private final Scanner scanner;

    public GrupoCursoVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n===== ASIGNACIONES (GRUPO - CURSO) =====");
        System.out.println("1. Asignar Curso a Grupo");
        System.out.println("2. Listar Asignaciones");
        System.out.println("3. Eliminar Asignación");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int[] pedirDatosAsignacion() {
        try {
            System.out.println("\n--- Nueva Asignación ---");
            System.out.print("ID Asignación: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("ID Grupo: ");
            int gid = Integer.parseInt(scanner.nextLine());
            System.out.print("ID Curso: ");
            int cid = Integer.parseInt(scanner.nextLine());
            return new int[]{id, gid, cid};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void listar(List<GrupoCurso> lista) {
        System.out.println("\n--- Listado ---");
        if(lista.isEmpty()) System.out.println("Sin asignaciones.");
        else lista.forEach(gc -> System.out.println("ID: " + gc.getId() + " | Grupo: " + gc.getGrupoId() + " <-> Curso: " + gc.getCursoId()));
    }

    public int pedirId() {
        System.out.print("ID de la asignación a eliminar: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarMensaje(String m) { System.out.println(m); }
}