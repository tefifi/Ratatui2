import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        RecipeExtractor extractor = new RecipeExtractor();
//        System.out.println("Extrayendo recetas...");
//        List<JSONObject> recetas = extractor.obtenerRecetas(80);
//        System.out.println("✅ Recetas extraídas: " + recetas.size());
//        extractor.guardarCSV(recetas);

        String recetasFile = GestorArchivo.leerArchivo("Data/recetas.csv");
        System.out.println("recetasFile = "+recetasFile);

        Usuario usuario = new Usuario();
        usuario.agregarCondicion();

        SistemaRecomendacion sistemaRecomendacion = new SistemaRecomendacion(usuario);

        sistemaRecomendacion.recomendarReceta();



    }
}
