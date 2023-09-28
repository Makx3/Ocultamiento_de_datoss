package org.example;
import java.util.Scanner;

public class Main {
    public static final int NUM_HABITACIONES = 10;
    public static final int PRECIO_SIN_ALIMENTACION = 30000;
    public static final int PRECIO_CON_ALIMENTACION = 45000;

    public static String[] estadoHabitaciones = new String[NUM_HABITACIONES];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarEstadoHabitaciones();
                    break;
                case 2:
                    reservarHabitacion();
                    break;
                case 3:
                    liberarHabitacion();
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema de administración del hotel.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Consultar estado de habitaciones");
        System.out.println("2. Reservar habitación");
        System.out.println("3. Liberar habitación");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void consultarEstadoHabitaciones() {
        System.out.println("\nEstado de las habitaciones:");
        for (int i = 0; i < NUM_HABITACIONES; i++) {
            System.out.println("Habitación " + (i + 1) + ": " + obtenerEstadoHabitacion(i));
        }
    }

    public static String obtenerEstadoHabitacion(int numeroHabitacion) {
        String estado = estadoHabitaciones[numeroHabitacion];
        if (estado == null) {
            return "Habitacion liberada";
        } else {
            return estado;
        }
    }

    public static void reservarHabitacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de habitación a reservar: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < NUM_HABITACIONES) {
            if (estadoHabitaciones[numeroHabitacion] == null) {
                System.out.print("¿Desea incluir alimentación? (S/N): ");
                char respuesta = scanner.next().charAt(0);
                estadoHabitaciones[numeroHabitacion] = (respuesta == 'S') ? "Ocupada con alimentación" : "Ocupada sin alimentación";
                System.out.println("Habitación " + (numeroHabitacion + 1) + " reservada con éxito.");
            } else {
                System.out.println("La habitación no está disponible para reservar.");
            }
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    public static void liberarHabitacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de habitación a liberar: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < NUM_HABITACIONES) {
            if (estadoHabitaciones[numeroHabitacion] != null) {
                estadoHabitaciones[numeroHabitacion] = null;
                System.out.println("Habitación " + (numeroHabitacion + 1) + " liberada con éxito.");
            } else {
                System.out.println("La habitación no está ocupada.");
            }
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }
}

