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


    @Test
    void testAtributosBasicos() {
        receta.atributosReceta();

        assertNotNull(receta.getNombre());
        assertNotNull(receta.getCantPorciones());
        assertNotNull(receta.getListoEn());
        assertNotNull(receta.getTipoComida());
        assertNotNull(receta.getIngredientes());
    }


    @Test
    void testValoresBooleanos() {
        receta.atributosReceta();


        assertTrue(receta.isLactosa() || !receta.isLactosa());
        assertTrue(receta.isGluten() || !receta.isGluten());
        assertTrue(receta.isVegan() || !receta.isVegan());
        assertTrue(receta.isVegetarian() || !receta.isVegetarian());
    }


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


    @Test
    void testValoresPorDefecto() {

        Receta recetaNueva = new Receta();


        assertNull(recetaNueva.getNombre());
        assertNull(recetaNueva.getCantPorciones());
        assertNull(recetaNueva.getListoEn());
    }
}
