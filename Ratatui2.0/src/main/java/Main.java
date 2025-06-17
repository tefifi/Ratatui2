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


        SistemaRecomendacion sistemaRecomendacion = new SistemaRecomendacion();
        Usuario usuario = new Usuario();
        Receta receta = new Receta();



        DatosNutricionales datosNutricionales = new DatosNutricionales();
        usuario.agregarCondicion();

        sistemaRecomendacion.recomendarReceta();



    }
}
