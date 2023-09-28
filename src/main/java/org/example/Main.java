{
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
