package org.example;

// En src/test/java/DatosNutricionalesTest.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatosNutricionalesTest {

    private DatosNutricionales datosNutricionales;

    @BeforeEach
    void setUp() {
        datosNutricionales = new DatosNutricionales(250, 15, 30, 10); // Calorías, Grasas, Carbohidratos, Proteínas
    }

    @Test
    void testGetCalorias() {
        assertEquals(250, datosNutricionales.getCalorias());
    }

    @Test
    void testGetGrasas() {
        assertEquals(15, datosNutricionales.getGrasas());
    }

    @Test
    void testGetCarbohidratos() {
        assertEquals(30, datosNutricionales.getCarbohidratos());
    }

    @Test
    void testGetProteinas() {
        assertEquals(10, datosNutricionales.getProteina());
    }
}
