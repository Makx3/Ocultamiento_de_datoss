package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    private static final int NUM_HABITACIONES = 10;
    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {

        for (int i = 0; i < NUM_HABITACIONES; i++) {
            Main.estadoHabitaciones[i] = null;
        }
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        // Restaurar la entrada y salida estándar originales
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }


    @Test
    public void consultarEstadoHabitaciones() {
        Main.estadoHabitaciones[0] = "Ocupada con alimentación";
        Main.estadoHabitaciones[1] = "Ocupada sin alimentación";
        Main.estadoHabitaciones[2] = "Reservada";

        String resultado = Main.obtenerEstadoHabitacion(0);

        assertEquals("Ocupada con alimentación", resultado);
    }

    @Test
    public void obtenerEstadoHabitacionLibreTest() {
        String estado = Main.obtenerEstadoHabitacion(2);

        assertEquals("Habitacion liberada", estado);
    }
    @Test
    public void reservarHabitacionTest() {
        String input = "3\nn\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.reservarHabitacion();

        String systemOutput = outputStream.toString();

        assertTrue(systemOutput.contains("Habitación 3 reservada con éxito."));
        assertEquals("Ocupada sin alimentación", Main.estadoHabitaciones[2]);
    }

    @Test
    public void liberarHabitacionTest() {
        // Configurar una habitación previamente ocupada y luego liberarla
        Main.estadoHabitaciones[1] = "Ocupada sin alimentación";

        // Comprobar que la habitación está ocupada antes de liberarla
        assertEquals("Ocupada sin alimentación", Main.estadoHabitaciones[1]);

        // Ejecutar el método de liberación de habitación
        Main.liberarHabitacion(); // Liberar la habitación actual

        // Comprobar que la habitación se ha liberado correctamente
        assertNull(Main.estadoHabitaciones[1]);
    }
}