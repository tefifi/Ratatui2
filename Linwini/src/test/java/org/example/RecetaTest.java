package org.example;

// En src/test/java/RecetaTest.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecetaTest {

    private Receta receta;

    @BeforeEach
    void setUp() {
        receta = new Receta();
        // Simular la carga de atributos desde un CSV
        receta.setNombre("Ensalada César");
        receta.setIngredientes("Lechuga, Pollo, Queso, Aderezo");
        receta.setTipoComida("Plato Principal");
        receta.setDatosNutricionales(new DatosNutricionales(200, 10, 5, 15)); // Calorías, Grasas, Carbohidratos, Proteínas
    }

    @Test
    void testGetNombre() {
        assertEquals("Ensalada César", receta.getNombre());
    }

    @Test
    void testGetIngredientes() {
        assertEquals("Lechuga, Pollo, Queso, Aderezo", receta.getIngredientes());
    }

    @Test
    void testGetTipoComida() {
        assertEquals("Plato Principal", receta.getTipoComida());
    }

    @Test
    void testGetDatosNutricionales() {
        DatosNutricionales datos = receta.getDatosNutricionales();
        assertNotNull(datos);
        assertEquals(200, datos.getCalorias());
        assertEquals(10, datos.getGrasas());
        assertEquals(5, datos.getCarbohidratos());
        assertEquals(15, datos.getProteinas());
    }
}
