package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numHabitaciones = 10;
        int[] estadoHabitaciones = new int[numHabitaciones];
        int[] nochesReservadas = new int[numHabitaciones];
        int precioSinAlimentacion = 30000;
        int precioConAlimentacion = 45000;

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarEstadoHabitaciones(estadoHabitaciones, nochesReservadas, precioSinAlimentacion, precioConAlimentacion);
                    break;
                case 2:
                    reservarHabitacion(scanner, estadoHabitaciones);
                    break;
                case 3:
                    liberarHabitacion(scanner, estadoHabitaciones, nochesReservadas);
                    break;
                case 4:
                    imprimirBoleta(scanner, estadoHabitaciones, nochesReservadas, precioSinAlimentacion, precioConAlimentacion);
                    break;
                case 5:
                    ingresarCantidadNoches(scanner, nochesReservadas, estadoHabitaciones);
                    break;
                case 6:
                    confirmarReserva(scanner, estadoHabitaciones);
                    break;
                case 7:
                    reiniciarHotel(scanner, estadoHabitaciones, nochesReservadas);
                    break;
                case 8:
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
        System.out.println("4. Imprimir boleta de habitación");
        System.out.println("5. Ingresar cantidad de noches");
        System.out.println("6. Confirmar reserva");
        System.out.println("7. Reiniciar hotel (clave: resetAll)");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void consultarEstadoHabitaciones(int[] estadoHabitaciones, int[] nochesReservadas, int precioSinAlimentacion, int precioConAlimentacion) {
        System.out.println("\nEstado de las habitaciones:");
        for (int i = 0; i < estadoHabitaciones.length; i++) {
            System.out.println("Habitación " + (i + 1) + ": " + obtenerEstadoHabitacion(i, estadoHabitaciones, nochesReservadas, precioSinAlimentacion, precioConAlimentacion));
        }
    }

    private static String obtenerEstadoHabitacion(int numeroHabitacion, int[] estadoHabitaciones, int[] nochesReservadas, int precioSinAlimentacion, int precioConAlimentacion) {
        int estado = estadoHabitaciones[numeroHabitacion];
        if (estado == 1) {
            int noches = nochesReservadas[numeroHabitacion];
            return "Ocupada (Noches reservadas: " + noches + ", Tipo de alimentación: " + ((precioSinAlimentacion == precioConAlimentacion) ? "Con alimentación" : "Sin alimentación") + ")";
        } else {
            return "Habitación liberada";
        }
    }

    private static void reservarHabitacion(Scanner scanner, int[] estadoHabitaciones) {
        System.out.print("Ingrese el número de habitación a reservar: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < estadoHabitaciones.length) {
            if (estadoHabitaciones[numeroHabitacion] != 1) {
                estadoHabitaciones[numeroHabitacion] = 1;
                System.out.print("¿Desea incluir alimentación? (S/N): ");
                char respuesta = scanner.next().charAt(0);
                System.out.println("Habitación " + (numeroHabitacion + 1) + " reservada con éxito.");
            } else {
                System.out.println("La habitación no está disponible para reservar.");
            }
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static void liberarHabitacion(Scanner scanner, int[] estadoHabitaciones, int[] nochesReservadas) {
        System.out.print("Ingrese el número de habitación a liberar: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < estadoHabitaciones.length) {
            if (estadoHabitaciones[numeroHabitacion] == 1) {
                estadoHabitaciones[numeroHabitacion] = 0;
                nochesReservadas[numeroHabitacion] = 0;
                System.out.println("Habitación " + (numeroHabitacion + 1) + " liberada con éxito.");
            } else {
                System.out.println("La habitación no está ocupada.");
            }
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static void imprimirBoleta(Scanner scanner, int[] estadoHabitaciones, int[] nochesReservadas, int precioSinAlimentacion, int precioConAlimentacion) {
        System.out.print("Ingrese el número de habitación para imprimir la boleta: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < estadoHabitaciones.length) {
            if (estadoHabitaciones[numeroHabitacion] == 1) {
                int noches = nochesReservadas[numeroHabitacion];
                int montoTotal = calcularMontoTotal(noches, precioSinAlimentacion, precioConAlimentacion);

                System.out.println("Boleta de la habitación " + (numeroHabitacion + 1));
                System.out.println("Cantidad de noches: " + noches);
                System.out.println("Tipo de alimentación: " + ((precioSinAlimentacion == precioConAlimentacion) ? "Con alimentación" : "Sin alimentación"));
                System.out.println("Monto total a cancelar: $" + montoTotal);
            } else {
                System.out.println("La habitación no está ocupada.");
            }
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static int calcularMontoTotal(int noches, int precioSinAlimentacion, int precioConAlimentacion) {
        int precioBase = (precioSinAlimentacion == precioConAlimentacion) ? precioSinAlimentacion : precioConAlimentacion;
        return precioBase * noches;
    }

    private static void ingresarCantidadNoches(Scanner scanner, int[] nochesReservadas, int[] estadoHabitaciones) {
        System.out.print("Ingrese el número de habitación para ingresar la cantidad de noches: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < nochesReservadas.length) {
            if (estadoHabitaciones[numeroHabitacion] == 1) {
                System.out.print("Ingrese la cantidad de noches: ");
                int cantidadNoches = scanner.nextInt();
                nochesReservadas[numeroHabitacion] = cantidadNoches;
                System.out.println("Cantidad de noches ingresada con éxito.");
            } else {
                System.out.println("La habitación no está ocupada.");
            }
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static void confirmarReserva(Scanner scanner, int[] estadoHabitaciones) {
        System.out.print("Ingrese el número de habitación para confirmar la reserva: ");
        int numeroHabitacion = scanner.nextInt() - 1;
        if (numeroHabitacion >= 0 && numeroHabitacion < estadoHabitaciones.length) {
            if (estadoHabitaciones[numeroHabitacion] == 1) {
                System.out.println("Reserva confirmada para la habitación " + (numeroHabitacion + 1) + ".");
            } else {
                System.out.println("La habitación no está ocupada.");
            }
        } else {
            System.out.println("Número de habitación no válido.");
        }
    }

    private static void reiniciarHotel(Scanner scanner, int[] estadoHabitaciones, int[] nochesReservadas) {
        System.out.print("Ingrese la clave para reiniciar el hotel (resetAll): ");
        scanner.nextLine(); // Consume el salto de línea pendiente
        String clave = scanner.nextLine();
        if (clave.equals("resetAll")) {
            for (int i = 0; i < estadoHabitaciones.length; i++) {
                estadoHabitaciones[i] = 0;
                nochesReservadas[i] = 0;
            }
            System.out.println("El hotel ha sido reiniciado. Todas las habitaciones están disponibles.");
        } else {
            System.out.println("Clave incorrecta. No se reinició el hotel.");
        }
    }
}
