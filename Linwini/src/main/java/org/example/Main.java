package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        //RecipeExtractor extractor = new RecipeExtractor();
        //System.out.println("Extrayendo recetas...");
        //List<JSONObject> recetas = extractor.obtenerRecetas(80);
        //System.out.println("✅ Recetas extraídas: " + recetas.size());
        //extractor.guardarCSV(recetas);

        // Inicia la interfaz gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }
}