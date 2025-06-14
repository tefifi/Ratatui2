import org.json.JSONObject;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        String recetasFile = GestorArchivo.leerArchivo("Files/recetas (1).csv");
        System.out.println("recetasFile = "+recetasFile);
        String[] datosRecetas = recetasFile.split("\n");

        Receta receta = new Receta();
        receta.atributosReceta();
        receta.mostrarReceta();


        RecipeExtractor extractor = new RecipeExtractor();
        System.out.println("Extrayendo recetas...");
        List<JSONObject> recetas = extractor.obtenerRecetas(80);
        System.out.println("✅ Recetas extraídas: " + recetas.size());
        extractor.guardarCSV(recetas);





    }
}
