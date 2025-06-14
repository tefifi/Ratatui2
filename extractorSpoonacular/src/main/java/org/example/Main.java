package org.example;

import org.json.JSONObject;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RecipeExtractor extractor = new RecipeExtractor();
        System.out.println("Extrayendo recetas...");
        List<JSONObject> recetas = extractor.obtenerRecetas(20);
        System.out.println("✅ Recetas extraídas: " + recetas.size());
        extractor.guardarCSV(recetas);
    }
}
