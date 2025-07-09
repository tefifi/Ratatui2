package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List; // Asegúrate de importar List

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue; // Si usas alguna aserción booleana

class DatosNutricionalesTest {

    private DatosNutricionales datosNutricionales;

    @BeforeEach
    void setUp() {
        datosNutricionales = new DatosNutricionales();
    }

    @Test
    void testInfoNutricionalPrimeraRecetaReal() throws IOException {

        datosNutricionales.infoNutricional(1);


        assertEquals(477, datosNutricionales.getCalorias());
        assertEquals(51, datosNutricionales.getCarbohidratos());
        assertEquals(20, datosNutricionales.getGrasas());
        assertEquals(26, datosNutricionales.getProteina());
        assertEquals(1335, datosNutricionales.getSodio());
        assertEquals(10, datosNutricionales.getAzucar());
        assertEquals(29, datosNutricionales.getColesterol());
        assertEquals(23, datosNutricionales.getFibra());
    }

    @Test
    void testInfoNutricionalInvalidIndex() {
        datosNutricionales.infoNutricional(-1);
        assertEquals(0, datosNutricionales.getCalorias());
    }


    @Test
    void testInfoNutricionalSegundaRecetaReal() throws IOException {

        datosNutricionales.infoNutricional(2);

        assertEquals(217, datosNutricionales.getCalorias());
        assertEquals(28, datosNutricionales.getCarbohidratos());
        assertEquals(7, datosNutricionales.getGrasas());
        assertEquals(10, datosNutricionales.getProteina());
        assertEquals(11, datosNutricionales.getSodio());
        assertEquals(11, datosNutricionales.getAzucar());
        assertEquals(0, datosNutricionales.getColesterol());
        assertEquals(11, datosNutricionales.getFibra());
    }
}
