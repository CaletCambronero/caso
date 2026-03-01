package org.example;

import org.example.Controlador.*;
import org.example.Repositorio.*;
import org.example.Servicio.*;
import org.example.Vista.*;
import org.example.misc.Conexion;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Verificar conexión al inicio
        Connection conn = Conexion.getConnection();
        if (conn == null) {
            System.out.println("FATAL: No se pudo conectar a la base de datos. Cerrando.");
            return;
        }

        // 1. Instanciar Repositorios
        EstudianteRepositorio estRepo = new EstudianteRepositorio();
        ProfesorRepositorio profRepo = new ProfesorRepositorio();
        CursoRepositorio cursoRepo = new CursoRepositorio();
        GrupoRepositorio grupoRepo = new GrupoRepositorio();
        GrupoCursoRepositorio gcRepo = new GrupoCursoRepositorio();

        // 2. Instanciar Servicios
        EstudianteServicio estService = new EstudianteServicio(estRepo);
        ProfesorServicio profService = new ProfesorServicio(profRepo);
        CursoServicio cursoService = new CursoServicio(cursoRepo);
        GrupoServicio grupoService = new GrupoServicio(grupoRepo);
        GrupoCursoServicio gcService = new GrupoCursoServicio(gcRepo, grupoRepo, cursoRepo);

        // 3. Instanciar Vistas (NUEVO)
        EstudianteVista estVista = new EstudianteVista();
        ProfesorVista profVista = new ProfesorVista(); // Asumiendo que ya creaste esta en el paso anterior
        CursoVista cursoVista = new CursoVista();
        GrupoVista grupoVista = new GrupoVista();
        GrupoCursoVista gcVista = new GrupoCursoVista();

        // 4. Instanciar Controladores (Inyectando Servicio + Vista)
        EstudianteControlador estController = new EstudianteControlador(estService, estVista);
        ProfesorControlador profController = new ProfesorControlador(profService, profVista);
        CursoControlador cursoController = new CursoControlador(cursoService, cursoVista);
        GrupoControlador grupoController = new GrupoControlador(grupoService, grupoVista);
        GrupoCursoControlador gcController = new GrupoCursoControlador(gcService, gcVista);

        // 5. Menú Principal
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== SISTEMA ACADÉMICO ===");
            System.out.println("1. Gestión de Estudiantes");
            System.out.println("2. Gestión de Profesores");
            System.out.println("3. Gestión de Cursos");
            System.out.println("4. Gestión de Grupos");
            System.out.println("5. Asignaciones (Grupos-Cursos)");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> estController.ejecutar();
                    case 2 -> profController.ejecutar();
                    case 3 -> cursoController.ejecutar();
                    case 4 -> grupoController.ejecutar();
                    case 5 -> gcController.ejecutar();
                    case 0 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
}