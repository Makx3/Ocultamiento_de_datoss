package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        final int NUM_HABITACIONES = 10;


        Habitacion[] habitaciones = new Habitacion[NUM_HABITACIONES];

        for (int i = 0; i < NUM_HABITACIONES; i++) {
            habitaciones[i] = new Habitacion();
        }

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarEstadoHabitaciones(habitaciones);
                    break;
                case 2:
                    reservarHabitacion(scanner, habitaciones);
                    break;
                case 3:
                    liberarHabitacion(scanner, habitaciones);
                    break;
                case 4:
                    ingresarCantidadNoches(scanner, habitaciones);
                    break;
                case 5:
                    confirmarReserva(scanner, habitaciones);
                    break;
                case 6:
                    System.out.println("Gracias por usar el sistema de administración del hotel.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Consultar estado de habitaciones");
        System.out.println("2. Reservar habitación");
        System.out.println("3. Liberar habitación");
        System.out.println("4. Ingresar cantidad de noches");
        System.out.println("5. Confirmar reserva");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void consultarEstadoHabitaciones(Habitacion[] habitaciones) {
        final int NUM_HABITACIONES = 10;
        System.out.println("\nEstado de las habitaciones:");
        for (int i = 0; i < NUM_HABITACIONES; i++) {
            System.out.println("Habitación " + (i + 1) + ": " + habitaciones[i].obtenerEstado());
        }
    }

    private static void reservarHabitacion(Scanner scanner, Habitacion[] habitaciones) {
        final int NUM_HABITACIONES = 10;

        System.out.print("Ingrese el número de habitación a reservar: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < NUM_HABITACIONES) {
            habitaciones[numeroHabitacion].reservar(scanner);
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static void liberarHabitacion(Scanner scanner, Habitacion[] habitaciones) {
        final int NUM_HABITACIONES = 10;

        System.out.print("Ingrese el número de habitación a liberar: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < NUM_HABITACIONES) {
            habitaciones[numeroHabitacion].liberar();
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static void ingresarCantidadNoches(Scanner scanner, Habitacion[] habitaciones) {
        final int NUM_HABITACIONES = 10;

        System.out.print("Ingrese el número de habitación para ingresar la cantidad de noches: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < NUM_HABITACIONES) {
            habitaciones[numeroHabitacion].ingresarNoches(scanner);
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static void confirmarReserva(Scanner scanner, Habitacion[] habitaciones) {
        final int NUM_HABITACIONES = 10;

        System.out.print("Ingrese el número de habitación para confirmar la reserva: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < NUM_HABITACIONES) {
            habitaciones[numeroHabitacion].confirmarReserva();
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }
}

class Habitacion {
    private String estado;
    private int nochesReservadas;

    public Habitacion() {
        this.estado = "Habitación liberada";
        this.nochesReservadas = 0;
    }

    public String obtenerEstado() {
        return estado + " (Noches reservadas: " + nochesReservadas + ")";
    }

    public void reservar(Scanner scanner) {
        if (estado.contains("liberada")) {
            System.out.print("¿Desea incluir alimentación? (S/N): ");
            char respuesta = scanner.next().charAt(0);
            estado = (respuesta == 'S') ? "Ocupada con alimentación" : "Ocupada sin alimentación";
            System.out.println("Habitación reservada con éxito.");
        } else {
            System.out.println("La habitación no está disponible para reservar.");
        }
    }

    public void liberar() {
        if (!estado.contains("liberada")) {
            estado = "Habitación liberada";
            nochesReservadas = 0;
            System.out.println("Habitación liberada con éxito.");
        } else {
            System.out.println("La habitación no está ocupada.");
        }
    }

    public void ingresarNoches(Scanner scanner) {
        if (!estado.contains("liberada")) {
            System.out.print("Ingrese la cantidad de noches: ");
            nochesReservadas = scanner.nextInt();
            System.out.println("Cantidad de noches ingresada con éxito.");
        } else {
            System.out.println("La habitación no está ocupada.");
        }
    }

    public void confirmarReserva() {
        if (!estado.contains("liberada")) {
            System.out.println("Reserva confirmada para la habitación.");
        } else {
            System.out.println("La habitación no está ocupada.");
        }
    }
}
