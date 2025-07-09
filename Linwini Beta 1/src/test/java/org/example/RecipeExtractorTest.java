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
        // Este test requiere que la API esté disponible y que se devuelvan recetas.
        // Se recomienda usar un mock para evitar depender de la API externa.
        List<JSONObject> recetas = extractor.obtenerRecetas(5);
        assertNotNull(recetas);
        assertTrue(recetas.size() <= 5); // Asegurarse de que no se obtienen más de 5 recetas
    }

    @Test
    void testObtenerRecetaInfo() {
        // Este test requiere que la API esté disponible y que se devuelvan recetas.
        // Se recomienda usar un mock para evitar depender de la API externa.
        JSONObject recetaInfo = extractor.obtenerRecetaInfoPublic(1); // Usar un ID de receta válido
        assertNotNull(recetaInfo);
        assertEquals(1, recetaInfo.getInt("id")); // Asegúrate de que el ID coincida
    }

    @Test
    void testGuardarCSV() throws IOException {
        // Configuración para el test
        Path tempFile = Files.createTempFile("recetas-test", ".csv");

        // 1. Crear datos de prueba
        JSONObject recetaMock = new JSONObject();
        recetaMock.put("title", "Ensalada de prueba");
        recetaMock.put("servings", 2);
        recetaMock.put("readyInMinutes", 15);
        // ... agregar más campos según sea necesario
        // 2. Cambiar temporalmente la ruta de salida
        extractor.OUTPUT_FILE = tempFile.toString();
        // 3. Ejecutar el método a probar
        extractor.guardarCSV(List.of(recetaMock));
        // 4. Verificar resultados
        String contenido = Files.readString(tempFile);
        assertTrue(contenido.contains("Ensalada de prueba"));
        assertTrue(contenido.contains("2")); // servings
        assertTrue(contenido.contains("15")); // readyInMinutes
        // Limpieza
        Files.delete(tempFile);
        System.out.println("Archivo temporal eliminado: " + tempFile);
    }

    @Test
    void testGetIngredientesIndirectamente() throws IOException {
        // Configurar
        Path tempFile = Files.createTempFile("test-ingredientes", ".csv");
        extractor.OUTPUT_FILE = tempFile.toString();

        // Crear receta de prueba
        JSONObject receta = new JSONObject();
        JSONArray ingredientes = new JSONArray();
        ingredientes.put(new JSONObject().put("original", "Tomate"));
        ingredientes.put(new JSONObject().put("original", "Lechuga"));
        receta.put("extendedIngredients", ingredientes);
        receta.put("title", "Test");

        // Ejecutar
        extractor.guardarCSV(List.of(receta));

        // Verificar
        String contenido = Files.readString(tempFile);
        assertTrue(contenido.contains("Tomate, Lechuga"));

        // Limpiar
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
