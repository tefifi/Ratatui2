
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//
//        RecipeExtractor extractor = new RecipeExtractor();
//        System.out.println("Extrayendo recetas...");
//        List<JSONObject> recetas = extractor.obtenerRecetas(80);
//        System.out.println("✅ Recetas extraídas: " + recetas.size());
//        extractor.guardarCSV(recetas);



        Usuario usuario = new Usuario();

        SistemaRecomendacion sistemaRecomendacion = new SistemaRecomendacion(usuario);


        System.out.println("¿Desea agregar alguna condicion?");
        switch (scanner.next().toLowerCase()){
            case "si":
                usuario.agregarCondicion();
                break;
            case "no":
                break;
            default:
                System.out.println("Escriba bien >:(");
        }

        System.out.println("¿Desea ver su indice de masa corporal?");
        switch (scanner.next().toLowerCase()){
            case "si":
                usuario.datosIMC();
                usuario.calcularIMC();
                break;
            case "no":
                break;
            default:
                System.out.println("Escriba bien >:(");
        }

        System.out.println("¿Desea agregar una receta?");
        String respuesta = scanner.next().toLowerCase();
        while (respuesta.equals("si")){

            sistemaRecomendacion.recomendarReceta();
            sistemaRecomendacion.agregarReceta();

            System.out.println("¿Deseas agregar otra receta?");
            respuesta = scanner.next().toLowerCase();
        }


    }
}
