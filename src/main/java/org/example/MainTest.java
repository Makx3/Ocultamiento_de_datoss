package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private Main hotel;
    private InputStream originalInput;
    private PrintStream originalOutput;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        originalInput = System.in;
        originalOutput = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void consultarEstadoHabitaciones() throws NoSuchFieldException, IllegalAccessException {
        hotel = new Main(3);
        String[] estadoHabitaciones = new String[3];
        estadoHabitaciones[0] = "Ocupada con alimentación";
        estadoHabitaciones[1] = "Ocupada sin alimentación";
        estadoHabitaciones[2] = "Reservada";
        setEstadoHabitaciones(hotel, estadoHabitaciones);

        hotel.consultarEstadoHabitaciones();

        String expectedOutput = "Estado de las habitaciones:\n" +
                "Habitación 1: Ocupada con alimentación\n" +
                "Habitación 2: Ocupada sin alimentación\n" +
                "Habitación 3: Reservada\n";
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void obtenerEstadoHabitacionLibreTest() throws NoSuchFieldException, IllegalAccessException {
        hotel = new Main(3);
        String[] estadoHabitaciones = new String[3];
        setEstadoHabitaciones(hotel, estadoHabitaciones);

        String estado = hotel.obtenerEstadoHabitacion(2);
        assertEquals("Habitación liberada", estado);
    }

    @Test
    public void reservarHabitacionTest() throws NoSuchFieldException, IllegalAccessException {
        hotel = new Main(3);
        String input = "3\nn\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        hotel.reservarHabitacion();

        String systemOutput = outputStream.toString();

        assertTrue(systemOutput.contains("Habitación 3 reservada con éxito."));

        Field estadoHabitacionesField = hotel.getClass().getDeclaredField("estadoHabitaciones");
        estadoHabitacionesField.setAccessible(true);
        String[] estadoHabitaciones = (String[]) estadoHabitacionesField.get(hotel);
        assertEquals("Ocupada sin alimentación", estadoHabitaciones[2]);
    }

    @Test
    public void liberarHabitacionTest() throws NoSuchFieldException, IllegalAccessException {
        hotel = new Main(3);
        String[] estadoHabitaciones = new String[3];
        estadoHabitaciones[1] = "Ocupada sin alimentación";
        setEstadoHabitaciones(hotel, estadoHabitaciones);

        assertEquals("Ocupada sin alimentación", estadoHabitaciones[1]);

        String input = "2\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        hotel.liberarHabitacion();

        Field estadoHabitacionesField = hotel.getClass().getDeclaredField("estadoHabitaciones");
        estadoHabitacionesField.setAccessible(true);
        estadoHabitaciones = (String[]) estadoHabitacionesField.get(hotel);
        assertNull(estadoHabitaciones[1]);
    }

    private void setEstadoHabitaciones(Main main, String[] estadoHabitaciones) throws NoSuchFieldException, IllegalAccessException {
        Field estadoHabitacionesField = main.getClass().getDeclaredField("estadoHabitaciones");
        estadoHabitacionesField.setAccessible(true);
        estadoHabitacionesField.set(main, estadoHabitaciones);
    }
}
