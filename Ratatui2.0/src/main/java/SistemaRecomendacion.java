import java.util.Scanner;

public class SistemaRecomendacion {
    Scanner scanner = new Scanner(System.in);

    private Receta receta = new Receta();
    private Usuario usuario = new Usuario();

    public void recomendarReceta(){
        System.out.println("Te recomendamos esta receta: "+receta.getNombre());
        receta.mostrarReceta();

    }

    public void agregarReceta(){
        System.out.println("Desea agregar la receta a su base de datos");
        String resp = scanner.nextLine();
        if (resp =="si"){
            usuario.agregarReceta(receta);
            System.out.println("Se agrego la receta: "+receta);
        }else if (resp == "no"){
            System.out.println("Saliendo del sistema");
        }else {
            System.out.println("Escribe bien >:(");
        }
    }

    public void filtrarReceta(){

        for (Condicion condicion : usuario.getCondiciones()){
            switch (condicion.getNombre()){
                case "diabetes":
                    if (condicion.obtenerValor() < receta.getDatosNutricionales().getAzucar()) {
                        System.out.println("Demasiado azúcar para esta persona con diabetes.");
                    }
                    break;

                case "hipertension":
                    if (condicion.obtenerValor() < receta.getDatosNutricionales().getSodio()) {
                        System.out.println("Demasiado sodio para esta persona con hipertensión.");
                    }
                    break;

                default:
                    System.out.println("Condición no reconocida: " + condicion.getNombre());

            }
        }
    }



}
