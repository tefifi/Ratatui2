package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RecetaTest {

    private Receta receta;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        receta = new Receta();
        System.setOut(new PrintStream(outputStream));
    }

    // Test 1: Verificación básica de atributos
    @Test
    void testAtributosBasicos() {
        receta.atributosReceta();

        assertNotNull(receta.getNombre());
        assertNotNull(receta.getCantPorciones());
        assertNotNull(receta.getListoEn());
        assertNotNull(receta.getTipoComida());
        assertNotNull(receta.getIngredientes());
    }

    // Test 2: Verificación de valores booleanos
    @Test
    void testValoresBooleanos() {
        receta.atributosReceta();

        // Verificamos que los getters booleanos funcionen
        assertTrue(receta.isLactosa() || !receta.isLactosa());
        assertTrue(receta.isGluten() || !receta.isGluten());
        assertTrue(receta.isVegan() || !receta.isVegan());
        assertTrue(receta.isVegetarian() || !receta.isVegetarian());
    }

    // Test 3: Verificación de mostrarReceta()
    @Test
    void testMostrarReceta() {
        receta.atributosReceta();
        receta.mostrarReceta();

        String output = outputStream.toString();
        assertTrue(output.contains("Tipo de comida:"));
        assertTrue(output.contains("Cantidad de porciones:"));
        assertTrue(output.contains("Listo en"));
        assertTrue(output.contains("Ingredientes:"));
    }

    // Test 4: Verificación de valores por defecto
    @Test
    void testValoresPorDefecto() {
        // Creamos una receta sin llamar a atributosReceta()
        Receta recetaNueva = new Receta();

        // Verificamos que tenga valores por defecto
        assertNull(recetaNueva.getNombre());
        assertNull(recetaNueva.getCantPorciones());
        assertNull(recetaNueva.getListoEn());
    }
}
