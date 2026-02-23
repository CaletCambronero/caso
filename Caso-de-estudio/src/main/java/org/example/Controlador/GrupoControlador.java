//package org.example.Controlador;
//
//import org.example.Modelo.Grupo;
//import org.example.Servicio.GrupoServicio;
//import java.util.Scanner;
//import java.util.List;
//import java.util.Optional;
//
//public class GrupoControlador {
//    private final GrupoServicio servicio;
//    private final Scanner scanner;
//
//    public GrupoControlador(GrupoServicio servicio) {
//        this.servicio = servicio;
//        this.scanner = new Scanner(System.in);
//    }
//
//    /**
//     * Inicia el bucle de interacción con el usuario para el módulo de grupos.
//     */
//    public void ejecutar() {
//        int opcion = -1;
//        while (opcion != 0) {
//            System.out.println("\n===== GESTIÓN DE GRUPOS =====");
//            System.out.println("1. Registrar nuevo grupo");
//            System.out.println("2. Listar todos los grupos");
//            System.out.println("3. Buscar grupo por ID");
//            System.out.println("4. Modificar grupo existente");
//            System.out.println("5. Eliminar grupo");
//            System.out.println("0. Volver al menú principal");
//            System.out.print("Seleccione una opción: ");
//
////            try {
////                opcion = Integer.parseInt(scanner.nextLine());
////                switch (opcion) {
////                    case 1 -> registrar();
////                    case 2 -> servicio.listar();
////                    case 3 -> servicio.buscarPorId();
////                    case 4 -> servicio.actualizar();
////                    case 5 -> servicio.eliminar();
////                    case 0 -> System.out.println("Saliendo del módulo de grupos...");
////                    default -> System.out.println("Opción no válida, intente de nuevo.");
////                }
//            } catch (NumberFormatException e) {
//                System.out.println("Error: Debe ingresar un valor numérico válido.");
//            } catch (Exception e) {
//                System.out.println("Error crítico: " + e.getMessage());
//            }
//        }
//    }
//
//    private void registrar() throws Exception {
//        System.out.println("\n--- Registro de Nuevo Grupo ---");
//        System.out.print("ID numérico del grupo: ");
//        int id = Integer.parseInt(scanner.nextLine());
//        System.out.print("Nombre del grupo (ej. G-01): ");
//        String nombre = scanner.nextLine();
//        System.out.print("Descripción del grupo: ");
//        String descripcion = scanner.nextLine();
//
//        // Se instancia usando el constructor de Grupo que llama a super() de ElementoAcademico [cite: Grupo.java, ElementoAc