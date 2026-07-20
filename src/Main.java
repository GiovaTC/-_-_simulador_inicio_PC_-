package principal;

import servicio.SimuladorInicio;

import java.util.Scanner;

/**
 * ==========================================================
 * Clase: Main
 * Proyecto: SimuladorInicioPC
 * Java: 21
 * ==========================================================
 *
 * Punto de entrada de la aplicación.
 *
 * Funcionalidades:
 *  - Iniciar la simulación del arranque del PC.
 *  - Mostrar un menú en consola .
 *  - Permitir repetir la simulación.
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {

            mostrarMenu();

            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {

                System.out.println("Opción no válida.");
                System.out.print("Seleccione una opción: ");
                scanner.next();

            }

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1 -> {

                    SimuladorInicio simulador = new SimuladorInicio();

                    simulador.iniciarSimulacion();

                }

                case 2 -> {

                    System.out.println();
                    System.out.println("Finalizando la aplicación...");
                    System.out.println("¡Hasta pronto!");

                }

                default -> {

                    System.out.println();
                    System.out.println("Opción incorrecta.");

                }

            }

            if (opcion != 2) {

                System.out.println();
                System.out.println("Presione ENTER para continuar...");
                scanner.nextLine(); // Limpia el buffer
                scanner.nextLine(); // Espera ENTER

            }

        } while (opcion != 2);

        scanner.close();

    }

    /**
     * Muestra el menú principal.
     */
    private static void mostrarMenu() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("      SIMULADOR DE ARRANQUE DEL PC");
        System.out.println("======================================");
        System.out.println();
        System.out.println("1. Iniciar simulación");
        System.out.println("2. Salir");
        System.out.println();
    }          
}