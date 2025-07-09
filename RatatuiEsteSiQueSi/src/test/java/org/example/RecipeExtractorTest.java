package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeExtractorTest {

    private RecipeExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new RecipeExtractor();
    }

    @Test
    void testObtenerRecetas() {

        List<JSONObject> recetas = extractor.obtenerRecetas(5);
        assertNotNull(recetas);
        assertTrue(recetas.size() <= 5);
    }

    @Test
    void testObtenerRecetaInfo() {


        JSONObject recetaInfo = extractor.obtenerRecetaInfoPublic(1);
        assertNotNull(recetaInfo);
        assertEquals(1, recetaInfo.getInt("id"));
    }

    @Test
    void testGuardarCSV() throws IOException {
        Path tempFile = Files.createTempFile("recetas-test", ".csv");


        JSONObject recetaMock = new JSONObject();
        recetaMock.put("title", "Ensalada de prueba");
        recetaMock.put("servings", 2);
        recetaMock.put("readyInMinutes", 15);


        extractor.OUTPUT_FILE = tempFile.toString();

        extractor.guardarCSV(List.of(recetaMock));

        String contenido = Files.readString(tempFile);
        assertTrue(contenido.contains("Ensalada de prueba"));
        assertTrue(contenido.contains("2")); // servings
        assertTrue(contenido.contains("15")); // readyInMinutes

        Files.delete(tempFile);
        System.out.println("Archivo temporal eliminado: " + tempFile);
    }

    @Test
    void testGetIngredientesIndirectamente() throws IOException {

        Path tempFile = Files.createTempFile("test-ingredientes", ".csv");
        extractor.OUTPUT_FILE = tempFile.toString();


        JSONObject receta = new JSONObject();
        JSONArray ingredientes = new JSONArray();
        ingredientes.put(new JSONObject().put("original", "Tomate"));
        ingredientes.put(new JSONObject().put("original", "Lechuga"));
        receta.put("extendedIngredients", ingredientes);
        receta.put("title", "Test");


        extractor.guardarCSV(List.of(receta));


        String contenido = Files.readString(tempFile);
        assertTrue(contenido.contains("Tomate, Lechuga"));


        Files.delete(tempFile);
    }


    @Test
    void testClasificarTipoComidaIndirectamente() throws IOException {
        // Configurar
        Path tempFile = Files.createTempFile("test-clasificar", ".csv");
        extractor.OUTPUT_FILE = tempFile.toString();

        // Crear receta de prueba
        JSONObject receta = new JSONObject();
        JSONArray dishTypes = new JSONArray();
        dishTypes.put("main course");
        receta.put("dishTypes", dishTypes);
        receta.put("title", "Test Recipe"); // Asegúrate de incluir el título

        // Ejecutar
        extractor.guardarCSV(List.of(receta));

        // Verificar que el tipo de comida se clasifica correctamente en el CSV
        String contenido = Files.readString(tempFile);
        assertTrue(contenido.contains("almuerzo/cena")); // Asegúrate de que el tipo de comida esté en el CSV

        // Limpiar
        Files.delete(tempFile);
    }


    @Test
    void testGetNutrientOrDefault() {
        JSONObject receta = new JSONObject();
        JSONObject nutrition = new JSONObject();
        JSONArray nutrientsArray = new JSONArray();
        nutrientsArray.put(new JSONObject().put("name", "Calories").put("amount", 200));
        nutrition.put("nutrients", nutrientsArray);
        receta.put("nutrition", nutrition);

        double calories = extractor.getNutrientOrDefault(receta, "Calories");
        assertEquals(200.0, calories);
    }

    @Test
    void testGetBooleanOrDefault() {
        JSONObject receta = new JSONObject();
        receta.put("vegan", true);
        boolean isVegan = extractor.getBooleanOrDefault(receta, "vegan");
        assertTrue(isVegan);
    }
}
