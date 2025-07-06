
import org.json.JSONObject;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        RecipeExtractor extractor = new RecipeExtractor();
//        System.out.println("Extrayendo recetas...");
//        List<JSONObject> recetas = extractor.obtenerRecetas(80);
//        System.out.println("✅ Recetas extraídas: " + recetas.size());
//        extractor.guardarCSV(recetas);
//
//

        Usuario usuario = new Usuario();

        SistemaRecomendacion sistemaRecomendacion = new SistemaRecomendacion(usuario);


        usuario.ingresarDatos();

        System.out.println("¿Desea agregar alguna condicion?");
        String resp = scanner.next().toLowerCase();
        while (!resp.equals("no")){
            switch (resp){
                case "si":
                    usuario.agregarCondicion();
                    System.out.println("¿Desea agregar otra condicion?");
                    resp = scanner.next().toLowerCase();
                    break;
                case "no":

                    break;
                default:
                    System.out.println("Opciones de respuesta: [Si] y [No]");
                    System.out.println("¿Desea agregar otra condicion?");
                    resp = scanner.next().toLowerCase();
                    break;
            }
        }
        usuario.mostrarCondiciones();


        System.out.println("¿Desea ver su indice de masa corporal?");
        String respu = scanner.next().toLowerCase();
        while (!respu.equals("no")) {

            switch (respu) {
                case "si":
                    usuario.datosIMC();
                    usuario.calcularIMC();
                    respu="no";
                    break;
                case "no":
                    break;
                default:
                    System.out.println("Opciones de respuesta: [Si] y [No]");
                    respu = scanner.nextLine();
                    break;
            }
        }

        System.out.println("¿Desea agregar una receta?");
        String respuesta = scanner.next().toLowerCase();
        scanner.nextLine(); // Limpiar buffer

        while (respuesta.equals("si")) {

                sistemaRecomendacion.recomendarReceta();

                sistemaRecomendacion.agregarReceta();

                System.out.println("¿Deseas agregar otra receta?");
                respuesta = scanner.next().toLowerCase();
                scanner.nextLine();

        }

        System.out.println("¿Desea ver las recetas guardadas?");
        if(scanner.next().toLowerCase().equals("si")){
            System.out.println("Tus recetas guardadas:");
            usuario.mostrarRecetas();
        }
        System.out.println("Gracias por usar esta app, le acabamos de robar todos sus datos");

    }


}
